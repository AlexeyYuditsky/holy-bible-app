package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper : BookDataToDomainMapper {

    override fun map(id: Int, name: String): BookDomain {
        return BookDomain(id, name)
    }

}