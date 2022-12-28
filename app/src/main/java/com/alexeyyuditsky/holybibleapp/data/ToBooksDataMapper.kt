package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract

interface ToBooksDataMapper : Abstract.Mapper {

    fun map(books: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData>

    class Base(
        private val toBookDataMapper: ToBookDataMapper
    ) : ToBooksDataMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookDataMapper>>): List<BookData> {
            return books.map { book ->
                book.map(toBookDataMapper)
            }
        }
    }

}