package com.mungaicodes.tomesanctuary.domain.repository

import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.util.Resource
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getSearchResults(query: String, filter: String): Flow<Resource<List<Book?>>>

    suspend fun findBookByVolumeId(volumeId: String): Flow<Resource<Book>>
}