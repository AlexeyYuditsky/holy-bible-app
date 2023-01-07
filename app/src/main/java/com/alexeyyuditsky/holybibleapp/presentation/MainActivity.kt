package com.alexeyyuditsky.holybibleapp.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.alexeyyuditsky.holybibleapp.core.BibleApp
import com.alexeyyuditsky.holybibleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel = (application as BibleApp).mainViewModel

        val adapter = BibleAdapter { viewModel.fetchBooks() }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        viewModel.observe(this) { books: List<BookUi> -> adapter.update(books) }
    }

}