package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper : BookDomainToUiMapper {

    override fun map(id: Int, name: String): BookUi {
        return BookUi.Base(id, name)
    }

}