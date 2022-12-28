package com.alexeyyuditsky.holybibleapp.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.alexeyyuditsky.holybibleapp.databinding.BookItemBinding
import com.alexeyyuditsky.holybibleapp.databinding.FailFullscreenBinding
import com.alexeyyuditsky.holybibleapp.databinding.ProgressFullscreenBinding

abstract class BibleViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(book: BookUi) = Unit

    class Base(
        private val binding: BookItemBinding
    ) : BibleViewHolder(binding) {

        override fun bind(book: BookUi) = with(binding) {
            val mapper: BookUi.StringMapper = object : BookUi.StringMapper {
                override fun map(text: String) {
                    name.text = text
                }
            }
            book.map(mapper)
        }

    }

    class Fail(
        private val binding: FailFullscreenBinding,
        private val retry: () -> Unit
    ) : BibleViewHolder(binding) {

        override fun bind(book: BookUi) = with(binding) {
            val mapper: BookUi.StringMapper = object : BookUi.StringMapper {
                override fun map(text: String) {
                    messageTextView.text = text
                }
            }
            tryAgainButton.setOnClickListener { retry.invoke() }
            book.map(mapper)
        }

    }

    class FullscreenProgress(
        binding: ProgressFullscreenBinding
    ) : BibleViewHolder(binding)

}