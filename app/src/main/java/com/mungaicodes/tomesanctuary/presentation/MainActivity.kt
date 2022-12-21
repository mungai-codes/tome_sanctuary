package com.mungaicodes.tomesanctuary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TomeSanctuaryTheme {

            }
        }
    }
}
