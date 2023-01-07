package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.BookDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.TestamentTemp
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(
    private val toDomainMapper: BookDataToDomainMapper
) : BooksDataToDomainMapper {

    override fun map(books: List<BookData>): BooksDomain {
        val data = ArrayList<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.matches(temp)) {
                if (temp.isEmpty()) {
                    val bookDomainTestament = BookDomain.Testament(TestamentType.OLD)
                    data.add(bookDomainTestament)
                } else {
                    val bookDomainTestament = BookDomain.Testament(TestamentType.NEW)
                    data.add(bookDomainTestament)
                }
                bookData.saveTestament(temp)
            }
            val bookDomain = bookData.map(toDomainMapper)
            data.add(bookDomain)
        }
        return BooksDomain.Success(data)
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