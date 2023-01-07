package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {

    data class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi {
            return mapper.map(id, name)
        }
    }

    data class Testament(
        private val type: TestamentType
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi {
            return type.map(mapper)
        }
    }

}