package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.R
import com.alexeyyuditsky.holybibleapp.domain.BookDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.TestamentType

class BaseBookDomainToUiMapper(
    private val resourceProvider: ResourceProvider
) : BookDomainToUiMapper {

    override fun map(id: Int, name: String): BookUi {
        return when (id) {
            TestamentType.OLD.getId() -> BookUi.Testament(id, resourceProvider.getString(R.string.old_testament))
            TestamentType.NEW.getId() -> BookUi.Testament(id, resourceProvider.getString(R.string.new_testament))
            else -> BookUi.Base(id, name)
        }
    }

}