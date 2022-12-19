package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.core.Book

interface BooksCacheDataSource {

    fun fetchBookDb(): List<BookDb>

    fun saveBooks(books: List<Book>)

    class Base(
        private val realmProvider: RealmProvider
    ) : BooksCacheDataSource {

        override fun fetchBookDb(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb: List<BookDb> = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<Book>) {
            realmProvider.provide().use {
                it.executeTransaction { realm ->
                    books.forEach { book ->
                        val bookDb: BookDb = realm.createObject(BookDb::class.java, book.id)
                        bookDb.name = book.name
                    }
                }
            }
        }

    }

}