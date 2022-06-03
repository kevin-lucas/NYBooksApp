package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.presentation.details.BookDetailsActivity

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        val recyclerBooks = findViewById<RecyclerView>(R.id.recyclerBooks)

        toolbarMain.title = getString(R.string.books_titles)
        setSupportActionBar(toolbarMain)

        val viewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer { books ->
            books?.let {
                with(recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.getBooks()
    }

}