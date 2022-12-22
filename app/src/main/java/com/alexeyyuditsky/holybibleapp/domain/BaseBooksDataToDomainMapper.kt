package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {

    override fun map(books: List<Book>): BooksDomain {
        return BooksDomain.Success(books)
    }

    override fun map(e: Exception): BooksDomain {
        return BooksDomain.Fail(e)
    }

}