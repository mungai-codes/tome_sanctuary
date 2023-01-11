package com.mungaicodes.tomesanctuary.data.repository

import android.util.Log
import com.mungaicodes.tomesanctuary.data.local.BookEntity
import com.mungaicodes.tomesanctuary.data.local.TomeSanctuaryDatabase
import com.mungaicodes.tomesanctuary.data.mapper.toBook
import com.mungaicodes.tomesanctuary.data.mapper.toBookEntity
import com.mungaicodes.tomesanctuary.data.mapper.toListOfBooks
import com.mungaicodes.tomesanctuary.data.remote.api.BookApiService
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.util.Constants.TAG
import com.mungaicodes.tomesanctuary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BookApiService,
    private val db: TomeSanctuaryDatabase
) : BooksRepository {

    private val dao = db.dao

    override suspend fun getSearchResults(
        query: String,
        filter: String
    ): Flow<Resource<List<Book?>>> = flow {
        emit(Resource.Loading())
        try {
            val books = api.searchVolumes(query = query, filter = filter).toListOfBooks()
            if (books.isEmpty()) {
                emit(Resource.Error("Volume not found!!"))
            } else {
                emit(Resource.Success(data = books.distinctBy { book -> book.volumeInfo?.title }))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ("Http Error: " + e.message)))
            Log.i(TAG, e.message.toString())

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ("IO Error: " + e.message)))
            Log.i(TAG, e.message.toString())
        }
    }

    override suspend fun findBookByVolumeId(volumeId: String): Flow<Resource<Book>> = flow {
        try {
            val book = api.findBookByVolumeId(volumeId).toBook()
            emit(Resource.Success(data = book))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ("Http Error: " + e.message)))
            Log.i(TAG, e.message.toString())

        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ("IO Error: " + e.message)))
            Log.i(TAG, e.message.toString())
        }
    }

    override suspend fun insertBook(book: Book) {
        dao.insertBook(book.toBookEntity())
    }

    override suspend fun getBookById(id: Int): BookEntity {
        return dao.getBookById(id)
    }

    override suspend fun deleteBook(book: Book) {
        dao.deleteBook(book.toBookEntity())
    }

    override fun getMyLibrary(): List<BookEntity> {
        return dao.getMyLibrary()
    }


}