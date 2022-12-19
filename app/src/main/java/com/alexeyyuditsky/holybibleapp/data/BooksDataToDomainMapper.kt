package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.domain.BookDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<Book>): BookDomain

    fun map(e: Exception): BookDomain

}