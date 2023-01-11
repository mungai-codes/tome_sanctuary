package com.mungaicodes.tomesanctuary.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TomeSanctuaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(
        bookEntity: BookEntity
    )

    @Query("Select * from mylibrary where id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

    @Query("Select * from mylibrary")
    fun getMyLibrary(): List<BookEntity>
}

