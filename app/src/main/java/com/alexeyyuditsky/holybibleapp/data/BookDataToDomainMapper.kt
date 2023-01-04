package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.domain.BookDomain

interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookDomain
}