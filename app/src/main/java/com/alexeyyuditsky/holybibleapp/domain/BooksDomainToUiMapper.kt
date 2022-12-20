package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {

    fun map(books: List<Book>): BooksUi

    fun map(errorType: ErrorType): BooksUi

}