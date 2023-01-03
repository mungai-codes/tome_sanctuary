package com.mungaicodes.tomesanctuary.presentation.home.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.presentation.home.components.*
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {

    val scaffoldState = rememberScaffoldState()
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToolBar {
                Text(
                    text = "Tome\r\nSanctuary",
                    color = TextWhite,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        },
        floatingActionButton = {
            FabButton()
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize(),
            color = TextWhite
        ) {
            Column(
                modifier = Modifier
            ) {

                Text(
                    text = "Categories",
                    color = Color.DarkGray,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 40.dp, bottom = 8.dp)
                )
                LazyVerticalGrid(
                    state = lazyGridState,
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 30.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(60.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                ) {
                    items(categories) { category ->
                        CategoryItem(
                            category = category,
                        ) {

                        }
                    }
                }

            }
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

