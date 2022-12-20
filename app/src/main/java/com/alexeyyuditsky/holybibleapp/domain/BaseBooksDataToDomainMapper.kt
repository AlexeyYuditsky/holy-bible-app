package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {

    override fun map(books: List<Book>): BookDomain {
        return BookDomain.Success(books)
    }

    override fun map(e: Exception): BookDomain {
        return BookDomain.Fail(e)
    }

}