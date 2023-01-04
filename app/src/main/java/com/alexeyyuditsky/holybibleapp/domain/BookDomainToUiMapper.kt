package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.presentation.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}