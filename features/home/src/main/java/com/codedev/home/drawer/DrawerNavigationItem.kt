package com.codedev.home.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorBlackText
import com.codedev.ui_base_lib.ColorGrey
import com.codedev.ui_base_lib.ColorWhiteText

@Composable
fun DrawerNavigationItem(
    title: String,
    imageVector: ImageVector,
    route: String,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onItemClick()
            }
            .background(if (selected) MaterialTheme.colors.primary.copy(alpha = 0.9f) else Color.Transparent)
            .padding(12.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = null, tint = if (selected) ColorWhiteText else ColorGrey)
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = if (selected) ColorWhiteText else ColorGrey
        )
    }
}