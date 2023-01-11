package com.mungaicodes.tomesanctuary.presentation.mylibrary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.home.components.FabButton
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.mylibrary.components.LibraryItemCard
import com.mungaicodes.tomesanctuary.presentation.mylibrary.components.libraryItems
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun MyLibraryScreen(
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToolBar(
                backgroundImage = painterResource(id = R.drawable.my_library),
                toolBarHeight = 150.dp
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = LampLight
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(200.dp)
                        .padding(end = 15.dp)
                ) {
                    Text(
                        text = "Bookshelf",
                        color = TextWhite,
                        fontSize = 35.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Here's what you like.",
                        textAlign = TextAlign.Start
                    )
                }
            }
        },
        floatingActionButton = {
            FabButton(
                bookMarkClicked = {
                },
                homeClicked = {
                    navController.navigate("home") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
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

            Column(modifier = Modifier) {

                Surface(color = LampLight) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                    }
                }

                LazyColumn(
                    modifier = Modifier.padding(top = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(libraryItems) { item ->
                        LibraryItemCard(item = item)
                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun MyLibraryScreenPreview() {
    TomeSanctuaryTheme {
        val nav = rememberNavController()
        MyLibraryScreen(navController = nav)
    }
}