package com.mungaicodes.tomesanctuary.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
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

@Composable
fun Holder2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .padding(10.dp)
        ) {
            val imagePainter = painterResource(id = categories[0].image)
            val imageOffset = 20.dp
            Card(
                shape = RoundedCornerShape(0.dp),
                backgroundColor = Color.DarkGray,
                modifier = Modifier
                    .padding(bottom = imageOffset)
                    .width(200.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        "Title",
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text("PM2.5")
                    Text(
                        "10",
                        fontSize = 35.sp,
                    )
                    Text("m/s")
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        "Very Good",
                        fontSize = 25.sp,
                    )
                    Spacer(
                        modifier = Modifier
                            .size(
                                with(LocalDensity.current) { imagePainter.intrinsicSize.height.toDp() - imageOffset }
                            )
                    )
                }
            }
            Image(
                painter = imagePainter,
                contentDescription = "",
                Modifier
                    .align(Alignment.BottomCenter)
                    .size(64.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun HolderPreview() {
    TomeSanctuaryTheme {
        Holder()
    }
}