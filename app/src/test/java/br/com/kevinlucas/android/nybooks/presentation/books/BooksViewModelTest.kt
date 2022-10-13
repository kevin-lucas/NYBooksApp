package br.com.kevinlucas.android.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.kevinlucas.android.nybooks.R
import br.com.kevinlucas.android.nybooks.data.BooksResult
import br.com.kevinlucas.android.nybooks.data.model.Book
import br.com.kevinlucas.android.nybooks.data.repository.BookRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks get success then sets booksLiveData`() {
        // Arrange
        val books = listOf(
            Book("title 1", "author 1", "description 1")
        )

        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get server error then sets viewFlipperLiveData`() {

        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500_generic))
    }

    @Test
    fun `when view model getBooks get api error code 401 sets viewFlipperLiveData`() {

        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultApiError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))
    }

    @Test
    fun `when view model getBooks get api error sets viewFlipperLiveData`() {

        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(400))
        viewModel = BooksViewModel(resultApiError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400_generic))
    }

    class MockRepository(private val result: BooksResult) : BookRepository {
        override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
            booksResultCallback(result)
        }

    }

}