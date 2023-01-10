package com.alexeyyuditsky.holybibleapp.presentation

import android.content.Context

interface UiDataCache {

    fun cache(newBooks: List<BookUi>)

    fun changeState(id: Int): List<BookUi>

    fun saveState()

    class Base(private val cacheId: IdCache) : UiDataCache {

        private val cachedList = ArrayList<BookUi>()

        override fun cache(newBooks: List<BookUi>) {
            cachedList.clear()
            // todo some logic with flags of collapsed
            cachedList.addAll(newBooks)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newBooks = ArrayList<BookUi>()
            val testamentBook = cachedList.find { bookUi ->
                bookUi.matches(id)
            }

            var add = false
            cachedList.forEachIndexed { index, bookUi ->
                if (bookUi is BookUi.Testament) {
                    if (bookUi == testamentBook) {
                        val element = bookUi.changeState()
                        cachedList[index] = element
                        newBooks.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newBooks.add(bookUi)
                        add = !bookUi.isCollapsed()
                    }
                } else {
                    if (add) newBooks.add(bookUi)
                }
            }

            return newBooks
        }

        override fun saveState() {
            cachedList.filter { bookUi ->
                bookUi.isCollapsed()
            }.forEach { bookUi ->
                bookUi.saveId(cacheId)
            }
            cacheId.finish()
        }

    }

}

interface IdCache {
    fun save(id: Int)
    fun finish()

    class Base(private val context: Context) : IdCache {
        override fun save(id: Int) {
            TODO("Not yet implemented")
        }

        override fun finish() {
            TODO("Not yet implemented")
        }
    }
}