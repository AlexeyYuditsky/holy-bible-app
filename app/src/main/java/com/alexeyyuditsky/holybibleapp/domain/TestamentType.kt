package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Matcher
import com.alexeyyuditsky.holybibleapp.presentation.BookUi

enum class TestamentType(
    private val id: Int,
) : Abstract.Object<BookUi, BookDomainToUiMapper>, Matcher<Int> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg: Int): Boolean = id == arg

    override fun map(mapper: BookDomainToUiMapper): BookUi {
        return mapper.map(id, name)
    }
}