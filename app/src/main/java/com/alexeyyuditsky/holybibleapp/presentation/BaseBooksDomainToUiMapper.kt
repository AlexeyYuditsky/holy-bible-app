package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.R
import com.alexeyyuditsky.holybibleapp.domain.BookDomain
import com.alexeyyuditsky.holybibleapp.domain.BookDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val toUiMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {

    override fun map(books: List<BookDomain>): BooksUi {
        val booksUi = books.map { bookDomain: BookDomain ->
            bookDomain.map(toUiMapper)
        }
        return BooksUi.Success(booksUi)
    }

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Fail(message)
    }

}