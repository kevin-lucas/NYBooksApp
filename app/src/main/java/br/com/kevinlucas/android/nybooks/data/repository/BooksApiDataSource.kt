package br.com.kevinlucas.android.nybooks.data.repository

import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.APIService
import br.com.kevinlucas.android.nybooks.data.BooksResult
import br.com.kevinlucas.android.nybooks.data.model.Book
import br.com.kevinlucas.android.nybooks.data.response.BookBodyResponse
import br.com.kevinlucas.android.nybooks.presentation.books.BooksViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BookRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {

        APIService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.booksResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }

                        booksResultCallback(BooksResult.Success(books))
                    }
                    else -> booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }
        })

    }

}