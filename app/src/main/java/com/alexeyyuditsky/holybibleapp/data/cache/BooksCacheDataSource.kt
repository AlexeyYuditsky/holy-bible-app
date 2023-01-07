package com.alexeyyuditsky.holybibleapp.data.cache

import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.BookDataToDbMapper

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDb>

    fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper,
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb: List<BookDb> = realm.where(BookDb::class.java).findAll()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<BookData>) {
            realmProvider.provide().use {
                it.executeTransaction { realm ->
                    books.forEach { book ->
                        book.mapToBookDb(mapper, DbWrapper.Base(realm))
                    }
                }
            }
        }

    }

}