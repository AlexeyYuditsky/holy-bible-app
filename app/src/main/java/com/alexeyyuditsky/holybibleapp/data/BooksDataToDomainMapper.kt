package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<Book>): BooksDomain

    fun map(e: Exception): BooksDomain

}