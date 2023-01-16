package com.mungaicodes.tomesanctuary.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mylibrary")
data class BookEntity(
    @PrimaryKey
    val id: String,
    val title: String?,
    val mediumThumbnail: String?,
    val authors: List<String?>,
)
