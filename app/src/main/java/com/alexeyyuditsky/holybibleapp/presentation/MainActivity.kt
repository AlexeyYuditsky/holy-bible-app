package com.alexeyyuditsky.holybibleapp.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.alexeyyuditsky.holybibleapp.core.BibleApp
import com.alexeyyuditsky.holybibleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { (application as BibleApp).mainViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = BibleAdapter(
            retry = { viewModel.fetchBooks() },
            collapse = { id: Int -> viewModel.collapseOrExpand(id) }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        viewModel.observe(this) { books: List<BookUi> -> adapter.update(books) }
    }

}