package com.alexeyyuditsky.holybibleapp.data.net

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.data.BooksData
import com.google.gson.annotations.SerializedName

/**
{"id": 1,"name": "Genesis","testament": "OT","genre": {"id": 1,"name": "Law"}}
 */
data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<Book, BookCloudMapper>() {

    override fun map(mapper: BookCloudMapper): Book {
        return mapper.map(id, name)
    }

}