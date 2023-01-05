package com.mungaicodes.tomesanctuary.data.repository

import android.util.Log
import com.mungaicodes.tomesanctuary.data.mapper.toBook
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
    private val api: BookApiService
) : BooksRepository {

    override suspend fun getSearchResults(query: String): Flow<Resource<List<Book?>>> = flow {
        emit(Resource.Loading())
        try {
            val books = api.searchVolumes(query).toListOfBooks()
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


}