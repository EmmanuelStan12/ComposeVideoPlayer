package com.codedev.home.offers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codedev.base.TopAppBarActionButton
import com.codedev.home.R
import com.codedev.ui_base_lib.ColorBlackText
import com.codedev.base.R as base_R

@Composable
fun OfferTopAppBar(
    onNavigate: () -> Unit
) {
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(base_R.drawable.ic_splash_icon), contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = "", tint = ColorBlackText)
            }
        },
        actions = {
            // search icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search"
            ) {

            }

            // lock icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Grid3x3,
                description = "Grid"
            ) {

            }

            // options icon (vertical dots)
            TopAppBarActionButton(
                imageVector = Icons.Outlined.MoreVert,
                description = "Options"
            ) {
                // show the drop down menu
                dropDownMenuExpanded = true
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}