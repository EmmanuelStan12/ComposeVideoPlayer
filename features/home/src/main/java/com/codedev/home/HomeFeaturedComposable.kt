package com.codedev.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codedev.ui_base_lib.ColorBlackText
import com.codedev.base.R as base_R

const val HOME = "composable.home"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeFeaturedComposable(
    onMenuClick: () -> Unit
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Surface(
                elevation = 4.dp,
                onClick = onMenuClick,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = base_R.drawable.ic_burger_menu),
                        contentDescription = null,
                        tint = ColorBlackText,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
            
            Text(text = "Welcome", style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.Black), color = ColorBlackText)
        }
    }
}