package com.mungaicodes.tomesanctuary.presentation.search

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.tomesanctuary.presentation.category.bottomElevation
import com.mungaicodes.tomesanctuary.presentation.category.components.BookItem
import com.mungaicodes.tomesanctuary.presentation.category.components.SheetContent
import com.mungaicodes.tomesanctuary.presentation.home.components.FabButton
import com.mungaicodes.tomesanctuary.presentation.home.components.ToolBar
import com.mungaicodes.tomesanctuary.presentation.search.util.filterList
import com.mungaicodes.tomesanctuary.presentation.search.util.keyWords
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.Red40
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite
import com.mungaicodes.tomesanctuary.util.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()
    val lazyGridState = rememberLazyGridState()
    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    val scope = rememberCoroutineScope()


    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false
    )

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch {
            modalBottomSheetState.hide()
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.ShowInfoToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is UiEvent.ShowIoSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(15.dp),
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            SheetContent(
                state.modalBook,
                onPreviewClick = {},
                onSampleClick = {},
                onSubscribe = {}
            ) {
                scope.launch {
                    state.modalBook?.id?.let { viewModel.insertBookToDatabase(it) }
                    delay(500L)
                    navController.navigate("mylibrary")
                }
            }
        },
        sheetElevation = 0.dp
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ToolBar {

                    Column {
                        Text(
                            text = "Discover",
                            color = TextWhite,
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(start = 15.dp),
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = "Search from a collection of 30 million and so books with over 10 million of them being free e-books!",
                            color = TextWhite,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            },
            floatingActionButton = {
                FabButton(
                    bookMarkClicked = {
                        navController.navigate("mylibrary")
                    },
                    homeClicked = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    },
                    searchClicked = {

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

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = LampLight,
                        elevation = 12.dp,
                        modifier = Modifier
                            .padding(
                                start = 15.dp,
                                top = 20.dp,
                                bottom = 8.dp,
                                end = 15.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            SearchBar(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = state.searchQuery,
                                onValueChange = viewModel::onQueryChanged,
                                keyword = keyWords[state.keyWordIndex].label,
                                filter = filterList[state.filterIndex].label,
                                scrollKeyWords = {
                                    viewModel.phaseThroughKeyWords()
                                },
                                scrollBookTypes = {
                                    viewModel.phaseThroughFilters()
                                },
                            ) {
                                viewModel.onSearch()
                            }
                        }
                    }
                    Surface(
                        shape = RectangleShape,
                        color = LampLight,
                        elevation = 12.dp,
                        modifier = Modifier
                            .padding(
                                start = 15.dp,
                                top = 5.dp,
                                bottom = 8.dp,
                                end = 15.dp
                            )
                            .bottomElevation()
                            .height(30.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (state.isLoading) "Loading..." else "${state.books.size} books found",
                                fontFamily = FontFamily.Serif,
                                color = if (state.books.isNotEmpty() && !state.isLoading) GreenGrey50 else if (state.books.isEmpty() && !state.isLoading) Red40 else Color.DarkGray,
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
                                BookItem(
                                    book = book,
                                ) {
                                    scope.launch {
                                        viewModel.getModalBook(book.id)
                                        modalBottomSheetState.show()
                                    }
                                }
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

}