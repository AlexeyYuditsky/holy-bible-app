package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Matcher

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper>, Matcher<Int> {

    override fun map(mapper: StringMapper) = Unit

    override fun matches(arg: Int): Boolean = false

    open fun isCollapsed(): Boolean = false

    open fun collapseOrExpand(collapse: CollapseListener) = Unit

    open fun showCollapsed(mapper: CollapseMapper) = Unit

    open fun changeState(): BookUi? = null

    open fun same(bookUi: BookUi): Boolean = false

    open fun sameContent(bookUi: BookUi): Boolean = false

    open fun saveId(cacheId: IdCache) = Unit

    object Progress : BookUi()

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false,
    ) : Info(id, name) {
        override fun collapseOrExpand(collapse: CollapseListener) {
            collapse.invoke(id)
        }

        override fun showCollapsed(mapper: CollapseMapper) {
            mapper.show(collapsed)
        }

        override fun isCollapsed(): Boolean {
            return collapsed
        }

        override fun changeState(): BookUi {
            return Testament(id, name, !collapsed)
        }

        override fun matches(arg: Int): Boolean {
            return arg == id
        }

        override fun sameContent(bookUi: BookUi): Boolean {
            return bookUi is Testament && bookUi.name == name && bookUi.collapsed == collapsed
        }

        override fun saveId(cacheId: IdCache) {
            cacheId.save(id)
        }
    }

    data class Base(
        override val id: Int,
        override val name: String,
    ) : Info(id, name) {
        override fun sameContent(bookUi: BookUi): Boolean {
            return bookUi is Base && bookUi.name == name
        }
    }

    data class Fail(
        private val message: String,
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }
    }

    abstract class Info(
        protected open val id: Int,
        protected open val name: String,
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }

        override fun same(bookUi: BookUi): Boolean {
            return bookUi is Info && bookUi.id == id
        }
    }

    // todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }

    interface CollapseMapper {
        fun show(collapsed: Boolean)
    }

}