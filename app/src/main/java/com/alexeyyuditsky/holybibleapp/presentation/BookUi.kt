package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUi() {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    data class Base(
        private val id: Int, // todo use for getting chapters
        private val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }
    }

    data class Fail(
        private val message: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }
    }

    // todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }

}