package com.alexeyyuditsky.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alexeyyuditsky.holybibleapp.core.Abstract

interface BooksCommunication : Abstract.Mapper {

    fun map(books: List<BookUi>)

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>)

    class Base : BooksCommunication {

        private val booksLiveData = MutableLiveData<List<BookUi>>()

        override fun map(books: List<BookUi>) {
            booksLiveData.value = books
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
            booksLiveData.observe(owner, observer)
        }

    }

}
