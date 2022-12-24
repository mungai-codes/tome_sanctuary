package com.mungaicodes.tomesanctuary.data.remote.dto

data class Response(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)