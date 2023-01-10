package com.alexeyyuditsky.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeyyuditsky.holybibleapp.domain.BooksDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val booksDomainToUiMapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
) : ViewModel() { // todo interface

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val booksDomain = booksInteractor.fetchBooks()
            val booksUi = booksDomain.map(booksDomainToUiMapper)
            booksUi.cache(uiDataCache)
            withContext(Dispatchers.Main) {
                booksUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        val books: List<BookUi> = uiDataCache.changeState(id)
        communication.map(books)
    }

    fun saveCollapsedState() {
        uiDataCache.saveState()
    }

    override fun onCleared() {
        saveCollapsedState()
        super.onCleared()
    }

}