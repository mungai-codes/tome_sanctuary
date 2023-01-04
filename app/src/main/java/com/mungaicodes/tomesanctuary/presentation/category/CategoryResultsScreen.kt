package com.mungaicodes.tomesanctuary.presentation.category

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.screens.search.components.BookItem2
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun CategoryScreen(
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()
    val lazyGridState = rememberLazyGridState()
    val viewModel: CategoryResultsViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ToolBar {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            .width(162.dp)
                            .padding(end = 10.dp)
                    ) {
                        Text(
                            text = "CATEGORY",
                            textAlign = TextAlign.End,
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = state.categoryTitle,
                            textAlign = TextAlign.End,
                            color = TextWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
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

                Surface(
                    shape = RectangleShape,
                    color = LampLight,
                    elevation = 12.dp,
                    modifier = Modifier
                        .padding(
                            start = 15.dp,
                            top = 20.dp,
                            bottom = 8.dp,
                            end = 15.dp
                        )
                        .bottomElevation()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${state.books.size} books found",
                            fontFamily = FontFamily.Serif,
                            color = GreenGrey50,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.ChevronLeft,
                                    contentDescription = "Back",
                                    tint = Color.DarkGray
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.ChevronRight,
                                    contentDescription = "Back",
                                    tint = GreenGrey50
                                )
                            }
                        }
                    }

                }

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
                    items(state.books) { book ->
                        if (book != null) {
                            BookItem2(book = book)
                        }
                    }
                }

            }
            if (state.isLoading) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

private fun Modifier.bottomElevation(): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = 8.dp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    TomeSanctuaryTheme {
        val navController = rememberNavController()
        CategoryScreen(navController)
    }
}