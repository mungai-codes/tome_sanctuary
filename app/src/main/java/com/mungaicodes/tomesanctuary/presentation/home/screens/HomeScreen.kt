package com.mungaicodes.tomesanctuary.presentation.home.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mungaicodes.tomesanctuary.presentation.home.components.CategoryItem
import com.mungaicodes.tomesanctuary.presentation.home.components.FabButton
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.home.components.categories
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun HomeScreen(
    navController: NavController
) {

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
            FabButton(
                bookMarkClicked = {

                },
                homeClicked = {
                    navController.navigate("home")
                    navController.popBackStack()
                },
                searchClicked = {
                    navController.navigate("search")
                }
            )
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
                            navController.navigate("category" + "?category=${category.title}&query=${category.query}")
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
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}

