package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BooksData
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
    ) : BooksInteractor {

        override suspend fun fetchBooks(): BooksDomain {
            val booksData: BooksData = booksRepository.fetchBooks()
            return booksData.map(mapper)
        }

    }

}