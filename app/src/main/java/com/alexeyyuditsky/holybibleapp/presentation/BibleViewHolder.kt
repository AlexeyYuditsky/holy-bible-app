package com.alexeyyuditsky.holybibleapp.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.holybibleapp.R

abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(book: BookUi) = Unit

    class FullscreenProgress(view: View) : BibleViewHolder(view)

    class Testament(
        view: View,
        private val collapse: CollapseListener,
    ) : Info(view) {

        private val collapseImageView: ImageView = view.findViewById(R.id.collapseImageView)
        private val testamentLayout: LinearLayout = view.findViewById(R.id.testament)

        override fun bind(book: BookUi) {
            super.bind(book)
            testamentLayout.setOnClickListener { book.collapseOrExpand(collapse) }
            book.showCollapsed(object : BookUi.CollapseMapper {
                override fun show(collapsed: Boolean) {
                    val iconId: Int = when (collapsed) {
                        true -> R.drawable.ic_expand
                        false -> R.drawable.ic_collapse
                    }
                    collapseImageView.setImageResource(iconId)
                }
            })
        }

    }

    class Base(view: View) : Info(view)

    class Fail(
        view: View,
        private val retry: RetryListener,
    ) : BibleViewHolder(view) {

        private val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        private val tryAgainButton: Button = view.findViewById(R.id.tryAgainButton)

        init {
            tryAgainButton.setOnClickListener { retry.invoke() }
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

    abstract class Info(view: View) : BibleViewHolder(view) {

        val nameTextView: TextView = view.findViewById(R.id.nameTextView)

        override fun bind(book: BookUi) {
            val mapper: BookUi.StringMapper = object : BookUi.StringMapper {
                override fun map(text: String) {
                    nameTextView.text = text
                }
            }
            book.map(mapper)
        }

    }

}