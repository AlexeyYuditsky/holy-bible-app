package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.domain.BooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {

    override fun map(books: List<Book>): BooksUi {
        return BooksUi.Success(communication, books)
    }

    override fun map(errorType: ErrorType): BooksUi {
        return BooksUi.Fail(communication, errorType, resourceProvider)
    }

}