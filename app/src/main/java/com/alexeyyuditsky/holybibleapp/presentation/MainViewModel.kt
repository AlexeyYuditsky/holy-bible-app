package com.alexeyyuditsky.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.holybibleapp.core.Abstract
import com.alexeyyuditsky.holybibleapp.core.Book
import com.alexeyyuditsky.holybibleapp.domain.BooksDomain
import com.alexeyyuditsky.holybibleapp.domain.BooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val booksDomainToUiMapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { // todo interface

    init {
        fetchBooks()
    }

    private fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val booksDomain: BooksDomain = booksInteractor.fetchBooks()
        val booksUi: BooksUi = booksDomain.map(booksDomainToUiMapper)
        withContext(Dispatchers.Main) {
            booksUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }

}