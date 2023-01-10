package com.alexeyyuditsky.holybibleapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.holybibleapp.R

typealias RetryListener = () -> Unit
typealias CollapseListener = (Int) -> Unit

class BibleAdapter(
    private val retry: RetryListener,
    private val collapse: CollapseListener,
) : RecyclerView.Adapter<BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(newBooks: List<BookUi>) {
          val diffUtilCallback = DiffUtilCallback(books, newBooks)
          val result = DiffUtil.calculateDiff(diffUtilCallback)
          books.clear()
          books.addAll(newBooks)
          result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (books[position]) {
            is BookUi.Testament -> R.layout.testament
            is BookUi.Base -> R.layout.book
            is BookUi.Fail -> R.layout.fail_fullscreen
            else -> R.layout.progress_fullscreen
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        return when (viewType) {
            R.layout.testament -> BibleViewHolder.Testament(createLayout(viewType, parent), collapse)
            R.layout.book -> BibleViewHolder.Base(createLayout(viewType, parent))
            R.layout.fail_fullscreen -> BibleViewHolder.Fail(createLayout(viewType, parent), retry)
            else -> BibleViewHolder.FullscreenProgress(createLayout(viewType, parent))
        }
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    private fun createLayout(viewType: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    }

}

