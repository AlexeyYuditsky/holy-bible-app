package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.BookDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(
    private val toDomainMapper: BookDataToDomainMapper
) : BooksDataToDomainMapper {

    override fun map(books: List<BookData>): BooksDomain {
        val booksDomain = books.map { bookData: BookData ->
            bookData.map(toDomainMapper)
        }
        return BooksDomain.Success(booksDomain)
    }

    override fun map(exception: Exception): BooksDomain {
        val errorType = when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
        return BooksDomain.Fail(errorType)
    }

}