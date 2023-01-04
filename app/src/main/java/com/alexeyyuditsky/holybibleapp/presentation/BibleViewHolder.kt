package com.alexeyyuditsky.holybibleapp.presentation

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.holybibleapp.R

abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(book: BookUi) = Unit

    class FullscreenProgress(view: View) : BibleViewHolder(view)

    class Base(view: View) : BibleViewHolder(view) {

        private val nameTextView = view.findViewById<TextView>(R.id.name)

        override fun bind(book: BookUi) {
            val mapper: BookUi.StringMapper = object : BookUi.StringMapper {
                override fun map(text: String) {
                    nameTextView.text = text
                }
            }
            book.map(mapper)
        }

    }

    class Fail(
        view: View,
        private val retry: () -> Unit
    ) : BibleViewHolder(view) {

        private val messageTextView = view.findViewById<TextView>(R.id.messageTextView)

        init {
            view.findViewById<Button>(R.id.tryAgainButton).setOnClickListener { retry.invoke() }
        }

        override fun bind(book: BookUi) {
            val mapper: BookUi.StringMapper = object : BookUi.StringMapper {
                override fun map(text: String) {
                    messageTextView.text = text
                }
            }
            book.map(mapper)
        }

    }

}