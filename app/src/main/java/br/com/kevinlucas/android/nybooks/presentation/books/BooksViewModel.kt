package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.APIService
import br.com.kevinlucas.android.nybooks.data.BooksResult
import br.com.kevinlucas.android.nybooks.data.model.Book
import br.com.kevinlucas.android.nybooks.data.repository.BookRepository
import br.com.kevinlucas.android.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel(val dataSource: BookRepository) : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        dataSource.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
                }
            }
        }
    }

    class ViewModelFactory(val dataSource: BookRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)){
                return BooksViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown viewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

}