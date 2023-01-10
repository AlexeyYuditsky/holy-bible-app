package com.alexeyyuditsky.holybibleapp.core

import android.app.Application
import com.alexeyyuditsky.holybibleapp.data.*
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.RealmProvider
import com.alexeyyuditsky.holybibleapp.data.network.BooksService
import com.alexeyyuditsky.holybibleapp.domain.BaseBookDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.domain.BaseBooksDataToDomainMapper
import retrofit2.Retrofit
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import com.alexeyyuditsky.holybibleapp.presentation.*
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        val service = retrofit.create(BooksService::class.java)

        val booksRepository = BooksRepository.Base(
            BooksCloudDataSource.Base(service, Gson()),
            BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDbMapper.Base()),
            ToBooksDataMapper.Base(ToBookDataMapper.Base())
        )

        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )

        val resourceProvider = ResourceProvider.Base(this)
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            BooksCommunication.Base(),
            UiDataCache.Base()
        )
    }

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

}