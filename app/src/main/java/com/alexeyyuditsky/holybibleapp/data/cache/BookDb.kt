package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.ToBookDataMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb(
    @PrimaryKey
    var id: Int = -1,
    var name: String = ""
) : RealmObject(), Abstract.Object<BookData, ToBookDataMapper> {

    override fun map(mapper: ToBookDataMapper): BookData {
        return mapper.map(id, name)
    }

}