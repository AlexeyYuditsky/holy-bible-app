package com.alexeyyuditsky.holybibleapp.data.network

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book

interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<BookCloud>): List<Book>

    class Base(
        private val bookMapper: BookCloudMapper
    ) : BooksCloudMapper {

        override fun map(cloudList: List<BookCloud>): List<Book> {
            return cloudList.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
        }

    }

}