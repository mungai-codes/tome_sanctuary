package com.mungaicodes.tomesanctuary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.mungaicodes.tomesanctuary.presentation.authentication.AuthViewModel
import com.mungaicodes.tomesanctuary.presentation.authentication.components.AuthScreen
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
        setContent {
            TomeSanctuaryTheme {
                AuthScreen(authViewModel = viewModel)
            }
        }
    }
}
