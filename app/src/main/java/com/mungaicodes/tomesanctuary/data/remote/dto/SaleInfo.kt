package com.mungaicodes.tomesanctuary.data.remote.dto

data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val saleability: String,
    val listPrice: ListPrice,
    val retailPrice: RetailPrice
)