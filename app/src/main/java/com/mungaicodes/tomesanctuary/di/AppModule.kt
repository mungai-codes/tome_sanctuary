package com.mungaicodes.tomesanctuary.di

import com.mungaicodes.tomesanctuary.data.remote.api.BookApiService
import com.mungaicodes.tomesanctuary.data.repository.BooksRepositoryImpl
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGoogleBooksApi(): BookApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BookApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBooksRepository(api: BookApiService): BooksRepository {
        return BooksRepositoryImpl(api)
    }
}