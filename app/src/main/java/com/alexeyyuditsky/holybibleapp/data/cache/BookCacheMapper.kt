package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCacheMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }
    }

}