package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.cache.BookCacheMapper
import com.alexeyyuditsky.holybibleapp.data.network.BookCloudMapper

abstract class BaseBooksRepositoryTest {

    protected inner class TestBookCloudMapper : BookCloudMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }
    }

    protected inner class TestBookCacheMapper : BookCacheMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }
    }

}