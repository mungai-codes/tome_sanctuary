package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.FileOpen
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun FabMenu(
    modifier: Modifier = Modifier,
    onBookMarkClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentWidth()
            .height(68.dp)
            .padding(10.dp),
        elevation = 12.dp,
        backgroundColor = StatusBar,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FabMenuItem(
                icon = Icons.Rounded.Bookmark,
                description = "bookmark",
                onClick = { onBookMarkClick() }
            )
            FabMenuItem(
                icon = Icons.Rounded.Home,
                description = "home",
                onClick = { onHomeClick() }
            )
            FabMenuItem(
                icon = Icons.Rounded.Search,
                description = "search",
                onClick = { onSearchClick() }
            )
        }

    }
}

@Composable
fun FabMenuItem(
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = TextWhite
        )
    }
}

@Composable
fun Fabtest() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FabMenu(onHomeClick = {}, onBookMarkClick = {}, onSearchClick = {})
            Spacer(modifier = Modifier.width(12.dp))
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.FileOpen, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FabMenuPreview() {
    TomeSanctuaryTheme {
        Fabtest()
    }
}