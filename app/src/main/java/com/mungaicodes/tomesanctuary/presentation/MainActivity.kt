package com.mungaicodes.tomesanctuary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mungaicodes.tomesanctuary.presentation.authentication.AuthViewModel
import com.mungaicodes.tomesanctuary.presentation.home.screens.HomeScreen
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
        ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This will lay out our app behind the system bars
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TomeSanctuaryTheme {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                DisposableEffect(key1 = systemUiController) {

                    systemUiController.setSystemBarsColor(
                        color = StatusBar,
                        darkIcons = useDarkIcons
                    )

                    onDispose { }
                }
                HomeScreen()
//                AuthScreen(authViewModel = viewModel)
            }
        }
    }
}
