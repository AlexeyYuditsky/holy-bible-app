package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheMapper
import com.alexeyyuditsky.holybibleapp.data.network.BookCloud
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudMapper

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository {

        override suspend fun fetchBooks(): BooksData = try {
            val booksCacheList: List<BookDb> = cacheDataSource.fetchBookDb()
            if (booksCacheList.isEmpty()) {
                val bookCloudList: List<BookCloud> = cloudDataSource.fetchBooks()
                val books: List<Book> = booksCloudMapper.map(bookCloudList)
                cacheDataSource.saveBooks(books)
                BooksData.Success(books)
            } else {
                val books: List<Book> = booksCacheMapper.map(booksCacheList)
                BooksData.Success(books)
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }

    }

}