package com.mungaicodes.tomesanctuary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mungaicodes.tomesanctuary.presentation.authentication.AuthViewModel
import com.mungaicodes.tomesanctuary.presentation.category.CategoryScreen
import com.mungaicodes.tomesanctuary.presentation.home.screens.HomeScreen
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

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

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        HomeScreen(navController = navController)
                    }

                    composable(
                        "category" + "?category={category}&query={query}",
                        arguments = listOf(
                            navArgument(
                                name = "category"
                            ) {
                                type = NavType.StringType
                                defaultValue = "CATEGORY"
                            },
                            navArgument(
                                name = "query"
                            ) {
                                type = NavType.StringType
                                defaultValue = "android"
                            }
                        )
                    ) {
                        CategoryScreen(navController = navController)
                    }
                }

            }
        }
    }
}
