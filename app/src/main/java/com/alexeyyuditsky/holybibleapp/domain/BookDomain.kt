package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.presentation.BookUi

class BookDomain(
    private val id: Int,
    private val name: String
) : Abstract.Object<BookUi, BookDomainToUiMapper> {

    override fun map(mapper: BookDomainToUiMapper): BookUi {
        return mapper.map(id, name)
    }

}