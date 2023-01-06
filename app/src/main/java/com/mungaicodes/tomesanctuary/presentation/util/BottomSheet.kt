package com.mungaicodes.tomesanctuary.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Book
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.presentation.home.components.Category
import com.mungaicodes.tomesanctuary.presentation.home.components.categories
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import kotlinx.coroutines.launch

@Composable
fun BottomSheet(
    category: Category,
    modifier: Modifier = Modifier
) {
    Surface(
        color = LampLight,
        modifier = modifier
            .height(600.dp)
            .fillMaxWidth(),
        elevation = 16.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.wrapContentHeight()) {
                Image(
                    modifier = Modifier
                        .width(250.dp)
                        .align(alignment = Alignment.TopEnd)
                        .offset(y = (-75).dp, x = 50.dp),
                    painter = painterResource(id = category.image),
                    contentDescription = category.description
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Holder() {

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            //. sheetContent
            BottomSheet(category = categories[0])
        },
        sheetShape = RoundedCornerShape(10.dp),
        sheetElevation = 0.dp,
        sheetBackgroundColor = LampLight,
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopAppBar")
                    }
                )
            }
        ) { innerPadding ->
            //...main content

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
            ) {
                IconButton(
                    onClick = {
                        scope.launch { modalBottomSheetState.show() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                }
                IconButton(
                    onClick = {
                        scope.launch { modalBottomSheetState.show() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                }
                IconButton(
                    onClick = {
                        scope.launch { modalBottomSheetState.show() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                }
                IconButton(
                    onClick = {
                        scope.launch { modalBottomSheetState.show() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Holder2() {

    BottomSheetWithAnchor()

}

@Composable
fun BottomSheetContent() {
    Surface(
        modifier = Modifier.height(300.dp),
        color = Color(0xff7353ba)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Modal Bottom Sheet",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
            Divider(
                modifier = Modifier.padding(5.dp),
                color = Color.White
            )
            Text(
                text = "Hellooo",
                fontSize = 15.sp,
                fontStyle = Italic,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalSheetWithAnchor(
    sheetState: ModalBottomSheetState,
    showModalSheet: MutableState<Boolean>
) {

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Rounded.Book, contentDescription = "anchor",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    showModalSheet.value = !showModalSheet.value
                    scope.launch { sheetState.show() }
                }
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetWithAnchor() {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scope = rememberCoroutineScope()
    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        scaffoldState = sheetScaffoldState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        sheetPeekHeight = 49.dp,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                }) {
                    val icon = if (sheetState.isExpanded) {
                        Icons.Filled.KeyboardArrowDown
                    } else {
                        Icons.Filled.KeyboardArrowUp
                    }

                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon button"
                    )
                }
                BottomSheetContent()
            }
        }
    ) {

    }
}

@Preview
@Composable
fun HolderPreview() {
    TomeSanctuaryTheme {
        Holder()
    }
}