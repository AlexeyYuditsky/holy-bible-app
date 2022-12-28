package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.network.BookCloud
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val toBooksDataMapper: ToBooksDataMapper,
    ) : BooksRepository {

        override suspend fun fetchBooks(): BooksData = try {
            val booksDb: List<BookDb> = cacheDataSource.fetchBooks()
            if (booksDb.isEmpty()) {
                val booksCloud: List<BookCloud> = cloudDataSource.fetchBooks()
                val booksData: List<BookData> = toBooksDataMapper.map(booksCloud)
                cacheDataSource.saveBooks(booksData)
                BooksData.Success(booksData)
            } else {
                val booksData: List<BookData> = toBooksDataMapper.map(booksDb)
                BooksData.Success(booksData)
            }
        } catch (exception: Exception) {
            BooksData.Fail(exception)
        }

    }

}