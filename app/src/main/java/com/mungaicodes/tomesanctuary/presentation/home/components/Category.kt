package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.annotation.DrawableRes
import com.mungaicodes.tomesanctuary.R

data class Category(
    val title: String,
    val description: String = "...",
    val query: String = "Android",
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
        title = "Art and Literature",
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
    Category(
        title = "History",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Autobiography",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Graphic Novel",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Children's Literature",
        image = R.drawable.code_icon
    ),
    Category(
        title = "Mystery",
        image = R.drawable.code_icon
    ),
)
