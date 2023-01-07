package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.DbWrapper

interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToBookDb(id: Int, name: String, testament: String, db: DbWrapper): BookDb

    class Base : BookDataToDbMapper {

        override fun mapToBookDb(id: Int, name: String, testament: String, db: DbWrapper): BookDb {
            val bookDb = db.createObject(id)
            bookDb.name = name
            bookDb.testament = testament
            return bookDb
        }

    }

}