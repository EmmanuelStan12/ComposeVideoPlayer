package com.codedev.storage_lib

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import com.codedev.data_lib.content_provider.ContentProviderVideo
import com.codedev.data_lib.models.Folder
import com.codedev.data_lib.models.Video
import com.freexitnow.context_provider_lib.ContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Collections
import java.util.concurrent.TimeUnit

object VideoContentProvider {

    private val VIDEO_URI: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Video.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    } else {
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    }

    fun createAndRegisterObserver(): ContentObserver {
        val observer: ContentObserver = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean, uris: MutableCollection<Uri>, flags: Int) {
                super.onChange(selfChange, uris, flags)
                Timber.e("selfChange: $selfChange")
                Timber.e("uris: ${uris.map { it.toString() }}")
            }

            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                Timber.e("selfChange: $selfChange")
            }

            override fun onChange(selfChange: Boolean, uri: Uri?) {
                super.onChange(selfChange, uri)
                Timber.e("selfChange: $selfChange, uri ${uri.toString()}")
            }

            override fun onChange(selfChange: Boolean, uri: Uri?, flags: Int) {
                super.onChange(selfChange, uri, flags)
                Timber.e("selfChange: $selfChange, uri: $uri, flags: $flags")
            }
        }

//        contentResolver?.registerContentObserver(VIDEO_URI, true, observer)

        return observer
    }

    suspend fun getVideoFolders(context: Context) = withContext(Dispatchers.IO) {
        val contentResolver = context.contentResolver

        val folders = mutableListOf<Folder>()

        val projection = arrayOf(
            MediaStore.Video.Media.DATA
        )

        val videoMap = mutableMapOf<String, Int>()

        // Query the MediaStore for video files
        val cursor: Cursor? = contentResolver?.query(VIDEO_URI, projection, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val pathColumn: Int = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            do {
                val videoPath: String = cursor.getString(pathColumn)
                val folderFullPath = videoPath.substring(0, videoPath.lastIndexOf("/"))
                val folderPath = folderFullPath.substring(folderFullPath.lastIndexOf("/")).removePrefix("/")
                videoMap[folderPath] = (videoMap[folderPath] ?: 0) + 1
            } while (cursor.moveToNext())
            cursor.close()
        }

        Log.d("VideoContentProvider", "map - $videoMap")

        return@withContext videoMap.map {entry ->
            Folder(
                entry.key,
                entry.value
            )
        }
    }

    suspend fun getVideos() = withContext(Dispatchers.IO) {
        val context: Context = ContextProvider.getApplication()
        val contentResolver = context.contentResolver

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATA
        )

        /*val selection = "${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(
            TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES).toString()
        )*/

        val result = mutableListOf<ContentProviderVideo>()

        val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

        val query = contentResolver.query(
            VIDEO_URI,
            projection,
            null,
            null,
            sortOrder
        )

        val videoMap = mutableMapOf<String, Int>()

        query?.use { cursor ->
            // Cache column indices.
            val pathColumn: Int = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                val videoPath: String = cursor.getString(pathColumn)
                val folderFullPath = videoPath.substring(0, videoPath.lastIndexOf("/"))
                val folderPath = folderFullPath.substring(folderFullPath.lastIndexOf("/")).removePrefix("/")
                videoMap[folderPath] = (videoMap[folderPath] ?: 0) + 1

                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getInt(durationColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    VIDEO_URI,
                    id
                )

                val video = ContentProviderVideo(
                    id,
                    name,
                    duration,
                    size,
                    contentUri.toString(),
                    folderPath,
                    videoPath
                )

                result += video
            }
        }

        val folders = videoMap.map {entry ->
            Folder(
                entry.key,
                entry.value
            )
        }

        Timber.d("ContentProviderList - $result")

        Pair(folders, result)
    }

    private fun extractVideoLocationInfo(context: Context, videoUri: Uri) {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(context, videoUri)
        } catch (e: RuntimeException) {
            Timber.e("Cannot retrieve video file", e)
        }
        // Metadata uses a standardized format.
        val locationMetadata: String? =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_LOCATION)
    }

    suspend fun getVideoThumbnail(videoPath: String): Bitmap? = withContext(Dispatchers.IO) {
        val context: Context = ContextProvider.getApplication()
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, Uri.parse(videoPath))
        val thumbnail = retriever.frameAtTime

        retriever.release()

        thumbnail
    }
}