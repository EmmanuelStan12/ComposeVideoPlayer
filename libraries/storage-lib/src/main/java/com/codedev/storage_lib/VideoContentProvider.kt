package com.codedev.storage_lib

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Collections

object VideoContentProvider {

    private val VIDEO_URI: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Video.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    } else {
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    }

    fun createAndRegisterObserver(): ContentObserver {
        val observer: ContentObserver = object: ContentObserver(null) {
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

        val folderPaths = mutableListOf<String>()

        val projection = arrayOf(
            MediaStore.Video.Media.DATA
        )

        // Query the MediaStore for video files

        // Query the MediaStore for video files
        val cursor: Cursor? = contentResolver?.query(VIDEO_URI, projection, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val pathColumn: Int = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            do {
                val videoPath: String = cursor.getString(pathColumn)
                val folderPath = videoPath.substring(0, videoPath.lastIndexOf("/"))
                folderPaths.add(folderPath)
            } while (cursor.moveToNext())
            cursor.close()
        }

        folderPaths
    }
}