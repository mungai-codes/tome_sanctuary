package com.mungaicodes.tomesanctuary.presentation.search.util

data class Filter(
    val label: String,
    val value: String
)

val filterList = listOf(
    Filter(
        label = "Limited",
        value = "partial"
    ),
    Filter(
        label = "Full",
        value = "full"
    ),
    Filter(
        label = "Free",
        value = "free-ebooks"
    ),
    Filter(
        label = "Paid",
        value = "paid-ebooks"
    ),
    Filter(
        label = "Ebooks",
        value = "ebooks"
    )
)
