package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.net.BookCloud
import com.alexeyyuditsky.holybibleapp.data.net.BookCloudMapper

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