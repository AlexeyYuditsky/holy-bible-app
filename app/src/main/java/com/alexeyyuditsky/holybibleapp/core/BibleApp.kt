package com.alexeyyuditsky.holybibleapp.core

import android.app.Application
import com.alexeyyuditsky.holybibleapp.data.BooksCloudDataSource
import com.alexeyyuditsky.holybibleapp.data.BooksCloudMapper
import com.alexeyyuditsky.holybibleapp.data.BooksRepository
import com.alexeyyuditsky.holybibleapp.data.cache.BookCacheMapper
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheMapper
import com.alexeyyuditsky.holybibleapp.data.cache.RealmProvider
import com.alexeyyuditsky.holybibleapp.data.net.BookCloudMapper
import com.alexeyyuditsky.holybibleapp.data.net.BooksService
import retrofit2.Retrofit
import com.alexeyyuditsky.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            // todo log interceptor
            .build()
        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val bookRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper()
        )
    }

}