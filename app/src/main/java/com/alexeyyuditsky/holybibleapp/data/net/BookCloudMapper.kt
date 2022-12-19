package com.alexeyyuditsky.holybibleapp.data.net

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book

interface BookCloudMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }
    }

}