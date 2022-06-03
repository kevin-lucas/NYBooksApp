package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.kevinlucas.android.nybooks.data.model.Book

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        booksLiveData.value = createFakeBooks()
    }

    private fun createFakeBooks() = listOf<Book>(
        Book("Title 1", "Author 1"),
        Book("Title 2", "Author 2"),
        Book("Title 3", "Author 3")
    )

}