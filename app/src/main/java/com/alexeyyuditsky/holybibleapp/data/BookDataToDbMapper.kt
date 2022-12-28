package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import io.realm.Realm

interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToBookDb(id: Int, name: String, realm: Realm): BookDb

    class Base : BookDataToDbMapper {

        override fun mapToBookDb(id: Int, name: String, realm: Realm): BookDb {
            val bookDb = realm.createObject(BookDb::class.java, id)
            bookDb.name = name
            return bookDb
        }

    }

}