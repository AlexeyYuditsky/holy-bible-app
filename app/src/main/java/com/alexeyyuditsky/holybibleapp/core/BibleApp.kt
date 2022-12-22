package com.alexeyyuditsky.holybibleapp.core

import android.app.Application
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudMapper
import com.alexeyyuditsky.holybibleapp.data.BooksRepository
import com.alexeyyuditsky.holybibleapp.data.cache.BookCacheMapper
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheMapper
import com.alexeyyuditsky.holybibleapp.data.cache.RealmProvider
import com.alexeyyuditsky.holybibleapp.data.network.BookCloudMapper
import com.alexeyyuditsky.holybibleapp.data.network.BooksService
import retrofit2.Retrofit
import com.alexeyyuditsky.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import com.alexeyyuditsky.holybibleapp.presentation.BaseBooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.presentation.BooksCommunication
import com.alexeyyuditsky.holybibleapp.presentation.MainViewModel
import com.alexeyyuditsky.holybibleapp.presentation.ResourceProvider
import io.realm.Realm

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            // todo log interceptor
            .build()
        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper()
        )

        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(communication, ResourceProvider.Base(this)),
            communication
        )
    }

}