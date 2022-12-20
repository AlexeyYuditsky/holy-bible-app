package com.alexeyyuditsky.holybibleapp.core

import android.app.Application
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import com.alexeyyuditsky.holybibleapp.presentation.BaseBooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.presentation.BooksCommunication
import com.alexeyyuditsky.holybibleapp.presentation.MainViewModel
import com.alexeyyuditsky.holybibleapp.presentation.ResourceProvider

class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val bookInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            bookInteractor,
            BaseBooksDomainToUiMapper(
                BooksCommunication.Base(),
                ResourceProvider.Base(this),
            ),
            BooksCommunication.Base()
        )
    }

}