package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), Abstract.Mapable<Book, BookCacheMapper> {

    @PrimaryKey
    var id: Int = -1
    var name: String = ""

    override fun map(mapper: BookCacheMapper): Book {
        return Book(id, name)
    }

}