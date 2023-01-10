package com.mungaicodes.tomesanctuary.presentation.search.util

data class KeyWord(
    val label: String,
    val value: String
)


val keyWords = listOf(
    KeyWord(
        label = "by title",
        value = "intitle:"
    ),
    KeyWord(
        label = "by author",
        value = "inauthor:"
    ),
    KeyWord(
        label = "by publisher",
        value = "inpublisher:"
    )
)



