package com.codedev.video.composables.video_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.codedev.base.getFormattedDuration
import com.codedev.data_lib.models.Folder
import com.codedev.data_lib.models.Video
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorWhiteText

@Composable
fun VideoItemComposable(
    video: Video,
    onVideoClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onVideoClick() }
            .padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .width(120.dp)
                .height(72.dp)
        ) {
            Image(
                bitmap = video.bitmap!!.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp)
                .clip(MaterialTheme.shapes.small)
                .background(
                    ColorBlackBackground
                )
                .padding(4.dp)) {
                Text(
                    text = video.duration.getFormattedDuration(),
                    style = MaterialTheme.typography.subtitle1,
                    color = ColorWhiteText
                )
            } 
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(verticalArrangement = Arrangement.Top) {
            Text(text = video.name, style = MaterialTheme.typography.body2, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }

}