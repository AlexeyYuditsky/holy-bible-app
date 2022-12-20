package com.alexeyyuditsky.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.domain.BookDomain
import com.alexeyyuditsky.holybibleapp.domain.BooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val booksDomainToUiMapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { // todo interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val bookDomain: BookDomain = booksInteractor.fetchBooks()
        val booksUi: BooksUi = bookDomain.map(booksDomainToUiMapper)
        with(Dispatchers.Main) {
            booksUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }

}