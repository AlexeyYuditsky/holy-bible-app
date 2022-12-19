package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book

interface BooksCacheMapper : Abstract.Mapper {

    fun map(booksDb: List<BookDb>): List<Book>

    class Base(
        private val mapper: BookCacheMapper
    ) : BooksCacheMapper {
        override fun map(booksDb: List<BookDb>): List<Book> {
            return booksDb.map { bookDb ->
                bookDb.map(mapper)
            }
        }
    }

}