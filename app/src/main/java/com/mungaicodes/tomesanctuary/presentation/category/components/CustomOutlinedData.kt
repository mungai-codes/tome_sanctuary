package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
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
        buttonLabel = "SUBSCRIBE",
        isEnabled = true
    ),
    CustomOutlinedButton(
        icon = Icons.Rounded.Book,
        description = "Read",
        buttonLabel = "READ",
        isEnabled = true
    )
)
