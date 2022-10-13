package br.com.kevinlucas.android.nybooks.data.repository

import br.com.kevinlucas.android.nybooks.data.BooksResult

interface BookRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)

}