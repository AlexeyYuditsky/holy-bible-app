package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {

    fun map(books: List<BookDomain>): BooksUi

    fun map(errorType: ErrorType): BooksUi

}