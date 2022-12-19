package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.data.net.BookCloud
import com.alexeyyuditsky.holybibleapp.data.net.BooksService

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val service: BooksService
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }

}