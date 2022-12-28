package com.alexeyyuditsky.holybibleapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.holybibleapp.databinding.BookItemBinding
import com.alexeyyuditsky.holybibleapp.databinding.FailFullscreenBinding
import com.alexeyyuditsky.holybibleapp.databinding.ProgressFullscreenBinding

class BibleAdapter(private val retry: () -> Unit) : RecyclerView.Adapter<BibleViewHolder>() {

    private var books: List<BookUi> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun update(newBooks: List<BookUi>) {
        this.books = newBooks
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (books[position]) {
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Progress -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        return when (viewType) {
            0 -> BibleViewHolder.Base(createBookItem(parent))
            1 -> BibleViewHolder.Fail(createFailFullscreen(parent), retry)
            else -> BibleViewHolder.FullscreenProgress(createProgressFullscreen(parent))
        }
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    private fun createBookItem(parent: ViewGroup): BookItemBinding {
        return BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun createFailFullscreen(parent: ViewGroup): FailFullscreenBinding {
        return FailFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun createProgressFullscreen(parent: ViewGroup): ProgressFullscreenBinding {
        return ProgressFullscreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

}

