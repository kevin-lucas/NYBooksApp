package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.model.Book

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        val recyclerBooks = findViewById<RecyclerView>(R.id.recyclerBooks)

        toolbarMain.title = getString(R.string.books_titles)
        setSupportActionBar(toolbarMain)

        with(recyclerBooks) {
            layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = BooksAdapter(getBooks())
        }

    }

    fun getBooks() = listOf<Book>(
        Book("Title 1", "Author 1"),
        Book("Title 2", "Author 2"),
        Book("Title 3", "Author 3"),
        Book("Title 4", "Author 4"),
        Book("Title 5", "Author 5"),
        Book("Title 6", "Author 6"),
    )
}