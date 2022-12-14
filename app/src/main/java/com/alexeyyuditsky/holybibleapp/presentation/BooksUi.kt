package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    abstract fun cache(uiDataCache: UiDataCache)

    data class Base(
        private val books: List<BookUi>,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }

        override fun cache(uiDataCache: UiDataCache) {
            uiDataCache.cache(books)
        }
    }

}