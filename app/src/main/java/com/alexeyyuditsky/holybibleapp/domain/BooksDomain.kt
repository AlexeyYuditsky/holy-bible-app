package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    data class Success(
        private val books: List<Book>
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(books)
        }
    }

    data class Fail(
        private val e: Exception
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            val errorType = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
            return mapper.map(errorType)
        }
    }

}