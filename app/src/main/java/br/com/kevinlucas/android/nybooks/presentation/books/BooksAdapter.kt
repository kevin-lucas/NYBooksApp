package br.com.kevinlucas.android.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.model.Book

class BooksAdapter(private val books: List<Book>, private val onItemClickListener: ((book: Book) -> Unit)) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(itemView: View, private val onItemClickListener: ((book: Book) -> Unit)) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.textTitle)
        private val author = itemView.findViewById<TextView>(R.id.textAuthor)

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onItemClickListener.invoke(book)
            }
        }
    }

}