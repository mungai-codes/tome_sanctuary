package com.mungaicodes.tomesanctuary.presentation.mylibrary.bookpreview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PreviewScreen(
    viewModel: BookPreviewViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.uiState.collectAsState().value
    val scaffoldState = rememberScaffoldState()

    BackHandler(true) {
        navController.navigate("mylibrary") {
            popUpTo("mylibrary") {
                inclusive = true
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("News App | WebView", color = Color.Green) },
                backgroundColor = TextWhite
            )
        },
        content = {
            AndroidView(factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true

                    settings.setSupportZoom(true)
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false

                    loadUrl(state.bookUrl)
                }
            }, update = {
                it.loadUrl(state.bookUrl)
            })
        }
    )
}