package com.mungaicodes.tomesanctuary.presentation.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun HomeScreen() {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToolBar {
                Text(
                    text = "Tome\r\nSanctuary",
                    color = TextWhite,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(modifier = Modifier.padding(paddingValues = innerPadding)) {
        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TomeSanctuaryTheme {
        HomeScreen()
    }
}

