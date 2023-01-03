package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.annotation.DrawableRes
import com.mungaicodes.tomesanctuary.R

data class Category(
    val title: String,
    val description: String = "...",
    val category: String = "Android",
    @DrawableRes
    val image: Int
)

val categories = listOf(
    Category(
        title = "Code",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Drama",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Cooking",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Education",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Art & Literature",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Fantasy",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Fiction",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Romance",
        image = R.drawable.code_icon
    ),
)