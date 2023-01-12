package com.mungaicodes.tomesanctuary.presentation.mylibrary

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.home.components.FabButton
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.mylibrary.components.LibraryItemCard
import com.mungaicodes.tomesanctuary.presentation.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyLibraryScreen(
    navController: NavController,
    viewModel: MyLibraryViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.uiState.collectAsState().value

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

                    items(state.myLibrary) { book ->

                        val bookId = book.id

                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    viewModel.deleteBookFromLibrary(bookId)
                                } else if (it == DismissValue.DismissedToEnd) {
                                    viewModel.refreshScreen()
                                    navController.navigate("preview" + "?bookId=${bookId}")
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(
                                DismissDirection.EndToStart,
                                DismissDirection.StartToEnd
                            ),
                            dismissThresholds = {
                                FractionalThreshold(0.2f)
                            },
                            background = {

                                val direction =
                                    dismissState.dismissDirection ?: return@SwipeToDismiss

                                val color by animateColorAsState(
                                    targetValue = when (dismissState.targetValue) {
                                        DismissValue.Default -> TextWhite
                                        DismissValue.DismissedToEnd -> DarkGreen80
                                        DismissValue.DismissedToStart -> Red80
                                    }
                                )

                                val icon = when (direction) {
                                    DismissDirection.StartToEnd -> Icons.Default.TravelExplore
                                    DismissDirection.EndToStart -> Icons.Default.Delete
                                }

                                val scale by animateFloatAsState(
                                    targetValue =
                                    if (dismissState.targetValue == DismissValue.Default) 0.8f else 1.2f
                                )

                                val alignment = when (direction) {
                                    DismissDirection.StartToEnd -> Alignment.CenterStart
                                    DismissDirection.EndToStart -> Alignment.CenterEnd
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(horizontal = 12.dp),
                                    contentAlignment = alignment
                                ) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = "icon",
                                            tint = LampLight,
                                            modifier = Modifier.scale(scale)
                                        )
                                    }
                                }
                            }
                        ) {
                            LibraryItemCard(
                                book = book,
                                elevation = animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 4.dp else 1.dp)
                            )
                        }
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