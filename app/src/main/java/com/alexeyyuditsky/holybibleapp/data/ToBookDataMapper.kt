package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract

interface ToBookDataMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookData

    class Base : ToBookDataMapper {
        override fun map(id: Int, name: String): BookData {
            return BookData(id, name)
        }
    }

}