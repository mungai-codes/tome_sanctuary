package com.mungaicodes.tomesanctuary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mungaicodes.tomesanctuary.presentation.screens.search.components.SearchScreen
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TomeSanctuaryTheme {
                SearchScreen()
            }
        }
    }
}
