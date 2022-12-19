package com.alexeyyuditsky.holybibleapp.core

import android.app.Application
import com.alexeyyuditsky.holybibleapp.data.BooksRepository
import com.alexeyyuditsky.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository: BooksRepository = TODO("merge")
        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper()
        )
    }

}