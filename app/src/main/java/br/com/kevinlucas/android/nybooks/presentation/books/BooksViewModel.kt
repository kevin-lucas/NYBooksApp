package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.kevinlucas.android.nybooks.data.APIService
import br.com.kevinlucas.android.nybooks.data.model.Book
import br.com.kevinlucas.android.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        APIService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.booksResults) {
                            val book = Book(
                                title = result.bookDetails[0].title,
                                author = result.bookDetails[0].author,
                                description = result.bookDetails[0].description,
                            )

                            books.add(book)
                        }
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}