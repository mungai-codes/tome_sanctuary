package com.mungaicodes.tomesanctuary.data.mapper

import com.mungaicodes.tomesanctuary.data.remote.dto.Response
import com.mungaicodes.tomesanctuary.domain.model.Book

fun Response.toListOfBooks(): List<Book> {
    return items.map { item ->
        Book(
            accessInfo = item.accessInfo,
            etag = item.etag,
            id = item.id,
            kind = item.kind,
            saleInfo = item.saleInfo,
            searchInfo = item.searchInfo,
            selfLink = item.selfLink,
            volumeInfo = item.volumeInfo
        )
    }
}