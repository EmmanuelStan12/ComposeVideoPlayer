package com.codedev.base.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.codedev.base.R
import com.codedev.ui_base_lib.MediumSpace
import com.codedev.ui_base_lib.SmallSpace

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DrawerNavigation() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_splash_icon),
                contentDescription = null,
                modifier = Modifier.size(54.dp)
            )
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
        Divider(
            Modifier.height(1.dp)
                .background(MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(MediumSpace))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DrawerRowOption(icon = R.drawable.ic_download, title = "Downloads") {

            }
            DrawerRowOption(icon = R.drawable.ic_add, title = "My List") {

            }
            DrawerRowOption(icon = R.drawable.ic_share, title = "VX Share") {

            }
            DrawerRowOption(icon = R.drawable.ic_music, title = "Local Music") {

            }
        }
        Spacer(modifier = Modifier.height(MediumSpace))
        DrawerNavigationItem(title = "App Language", icon = Icons.Outlined.Language) {

        }
        DrawerNavigationItem(title = "App Theme", icon = Icons.Outlined.Colorize) {

        }
    }
}

@Composable
fun DrawerRowOption(
    @DrawableRes icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier
                .clickable { onClick() },
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(8.dp)
                    .align(CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(SmallSpace))
        Text(text = title, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun DrawerNavigationItem(
    icon: ImageVector? = null,
    @DrawableRes iconRes: Int? = null,
    title: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = MediumSpace, vertical = SmallSpace)
    ) {
        if (icon != null)
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(30.dp))
        else
            Icon(painterResource(id = iconRes!!), contentDescription = null, modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.width(MediumSpace))
        Text(text = title, style = MaterialTheme.typography.body2)
    }
}