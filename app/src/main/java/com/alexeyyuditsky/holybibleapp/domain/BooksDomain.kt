package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.presentation.BooksUi

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    data class Success(
        private val books: List<BookDomain>,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(books)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(errorType)
        }
    }

}