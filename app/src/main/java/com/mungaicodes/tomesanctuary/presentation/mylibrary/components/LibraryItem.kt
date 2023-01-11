package com.mungaicodes.tomesanctuary.presentation.mylibrary.components

import androidx.annotation.DrawableRes
import com.mungaicodes.tomesanctuary.R

data class LibraryItem(
    @DrawableRes
    val image: Int,
    val author: String,
    val title: String
)

val libraryItems = listOf(
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    ),
    LibraryItem(
        image = R.drawable.code_icon,
        author = "Moses Mungai",
        title = "The Android Development Journey and Path to Tech Bron'es"
    )
)
