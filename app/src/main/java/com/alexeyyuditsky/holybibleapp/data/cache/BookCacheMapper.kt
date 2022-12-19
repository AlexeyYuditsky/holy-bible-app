package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(bookDb: BookDb): Book

    class Base : BookCacheMapper {
        override fun map(bookDb: BookDb): Book {
            return Book(bookDb.id, bookDb.name)
        }
    }

}