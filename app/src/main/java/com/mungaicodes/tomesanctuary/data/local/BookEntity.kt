package com.mungaicodes.tomesanctuary.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mylibrary")
data class BookEntity(
    val id: String,
    val title: String?,
    val mediumThumbnail: String?,
    val authors: List<String?>,
    @PrimaryKey val bookId: Int? = null
)
