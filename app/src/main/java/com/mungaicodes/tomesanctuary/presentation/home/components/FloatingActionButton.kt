package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar

@Composable
fun FabButton(
    bookMarkClicked: () -> Unit,
    homeClicked: () -> Unit,
    searchClicked: () -> Unit
) {

    var isCollapsed by remember {
        mutableStateOf(false)
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        AnimatedVisibility(visible = isCollapsed) {
            FabMenu(
                onBookMarkClick = { bookMarkClicked() },
                onHomeClick = { homeClicked() },
                onSearchClick = { searchClicked() }
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        FloatingActionButton(
            onClick = {
                isCollapsed = !isCollapsed
            },
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            backgroundColor = StatusBar
        ) {
            if (isCollapsed) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_fab),
                    contentDescription = "close fab"
                )
            } else
                Icon(
                    painter = painterResource(id = R.drawable.ic_open_fab),
                    contentDescription = "open fab",
                    tint = Color.DarkGray
                )
        }
    }


}

