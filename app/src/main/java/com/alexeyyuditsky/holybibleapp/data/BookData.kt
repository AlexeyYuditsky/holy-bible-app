package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.domain.BookDomain
import io.realm.Realm

class BookData(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>, ToBookDb<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name)
    }

    override fun mapToBookDb(mapper: BookDataToDbMapper, realm: Realm): BookDb {
        return mapper.mapToBookDb(id, name, realm)
    }

}

// todo make it better later
interface ToBookDb<T, M : Abstract.Mapper> {
    fun mapToBookDb(mapper: M, realm: Realm): T
}