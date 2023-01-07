package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.DbWrapper
import com.alexeyyuditsky.holybibleapp.domain.BookDomain

data class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String,
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, ToBookDb<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name)
    }

    override fun mapToBookDb(mapper: BookDataToDbMapper, db: DbWrapper): BookDb {
        return mapper.mapToBookDb(id, name, testament, db)
    }

    fun matches(testamentTemp: TestamentTemp): Boolean {
        return testamentTemp.matches(testament)
    }

    fun saveTestament(testamentTemp: TestamentTemp) {
        testamentTemp.save(testament)
    }

}

interface TestamentTemp {
    fun save(testament: String)
    fun matches(testament: String): Boolean
    fun isEmpty(): Boolean

    class Base : TestamentTemp {
        private var temp = ""
        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String): Boolean = temp == testament
        override fun isEmpty(): Boolean = temp.isEmpty()
    }
}

// todo make it better later
interface ToBookDb<T, M : Abstract.Mapper> {
    fun mapToBookDb(mapper: M, db: DbWrapper): T
}