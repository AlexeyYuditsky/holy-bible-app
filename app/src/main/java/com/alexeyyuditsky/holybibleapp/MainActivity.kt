package com.alexeyyuditsky.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.alexeyyuditsky.holybibleapp.core.BibleApp
import com.alexeyyuditsky.holybibleapp.presentation.BibleAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = (application as BibleApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter()
        recyclerView.adapter = adapter

        viewModel.observe(this) { adapter.update(it) }
        viewModel.fetchBooks()
        // todo observe fail
    }
}