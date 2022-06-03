package br.com.kevinlucas.android.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(val books : List<Book>) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.textTitle
        private val author = itemView.textAuthor

        fun bindView(book : Book){
            title.text = book.title
            author.text = book.author
        }
    }

}