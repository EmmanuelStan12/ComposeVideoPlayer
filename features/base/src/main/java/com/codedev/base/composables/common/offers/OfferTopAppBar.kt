package com.codedev.home.common.offers

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codedev.base.TopAppBarActionButton
import com.codedev.home.R
import com.codedev.ui_base_lib.ColorBlackText
import com.codedev.base.R as base_R

@Preview
@Composable
fun OfferTopAppBar() {
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle (
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                letterSpacing = 1.sp
                            )
                        ) {
                            append("VX")
                        }
                        withStyle(
                            SpanStyle(
                                fontStyle = FontStyle.Italic,
                                textDecoration = TextDecoration.Underline,
                                fontSize = 22.sp,
                                letterSpacing = 2.sp
                            )
                        ) {
                            append("Player")
                        }
                    }
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