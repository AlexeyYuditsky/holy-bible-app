package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.domain.BookDomain
import io.realm.Realm

class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, ToBookDb<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name)
    }

    override fun mapToBookDb(mapper: BookDataToDbMapper, realm: Realm): BookDb {
        return mapper.mapToBookDb(id, name, testament, realm)
    }

    fun compare(testamentTemp: TestamentTemp): Boolean = testamentTemp.matches(testament)
    fun saveTestament(testamentTemp: TestamentTemp) = testamentTemp.save(testament)
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
    fun mapToBookDb(mapper: M, realm: Realm): T
}