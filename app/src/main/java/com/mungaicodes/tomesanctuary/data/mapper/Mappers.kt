package com.mungaicodes.tomesanctuary.data.mapper

import com.mungaicodes.tomesanctuary.data.local.BookEntity
import com.mungaicodes.tomesanctuary.data.remote.dto.Item
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

fun Item.toBook(): Book {
    return Book(
        accessInfo = accessInfo,
        etag = etag,
        id = id,
        kind = kind,
        saleInfo = saleInfo,
        searchInfo = searchInfo,
        selfLink = selfLink,
        volumeInfo = volumeInfo
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = volumeInfo?.title,
        thumbnail = volumeInfo?.imageLinks?.thumbnail,
        authors = if (volumeInfo?.authors == null) listOf("No Authors") else volumeInfo.authors
    )
}