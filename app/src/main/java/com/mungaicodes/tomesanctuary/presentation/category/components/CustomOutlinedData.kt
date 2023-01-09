package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Subscriptions
import androidx.compose.material.icons.rounded.TextSnippet
import androidx.compose.ui.graphics.vector.ImageVector

data class CustomOutlinedButton(
    val icon: ImageVector,
    val description: String? = null,
    val buttonLabel: String,
    val isEnabled: Boolean = true
)

val customOutlinedButtons = listOf(
    CustomOutlinedButton(
        icon = Icons.Rounded.MenuBook,
        description = "preview button",
        buttonLabel = "PREVIEW",
        isEnabled = true
    ),
    CustomOutlinedButton(
        icon = Icons.Rounded.Subscriptions,
        description = "subscribe",
        buttonLabel = "subscribe",
        isEnabled = true
    ),
    CustomOutlinedButton(
        icon = Icons.Rounded.TextSnippet,
        description = "sample",
        buttonLabel = "sample",
        isEnabled = true
    )
)
