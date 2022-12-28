package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    class Success(
        private val books: List<BookUi>,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }
    }

    class Fail(
        private val message: String,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }

}