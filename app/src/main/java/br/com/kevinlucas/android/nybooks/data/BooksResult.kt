package br.com.kevinlucas.android.nybooks.data

import br.com.kevinlucas.android.nybooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}