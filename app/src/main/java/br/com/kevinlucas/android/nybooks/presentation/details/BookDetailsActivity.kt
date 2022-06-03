package br.com.kevinlucas.android.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.presentation.base.BaseActivity

class BookDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)

        setupToolbar(toolbarMain, R.string.books_details_title, true)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        val tvTitle = findViewById<TextView>(R.id.tvBookDetailsTitle)
        val tvDescription = findViewById<TextView>(R.id.tvBookDetailsDescription)

        tvTitle.text = title
        tvDescription.text = description
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "DESCRIPTION"

        /**
         * Simplifica a chamada da atividade, desclarando quais os valores que a intent precisa
         * para funcionar corretamente e centralizado na propia atividade para uso de outros
         */
        fun getStartIntent(context: Context, title: String, description: String) : Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}