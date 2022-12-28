package com.alexeyyuditsky.holybibleapp.data.network

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.ToBookDataMapper
import com.google.gson.annotations.SerializedName

/**
{"id": 1,"name": "Genesis","testament": "OT","genre": {"id": 1,"name": "Law"}}
 */
data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<BookData, ToBookDataMapper> {

    override fun map(mapper: ToBookDataMapper): BookData {
        return mapper.map(id, name)
    }

}
