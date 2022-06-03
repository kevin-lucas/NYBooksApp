package br.com.kevinlucas.android.nybooks.data.response

import br.com.kevinlucas.android.nybooks.data.model.Book
import com.google.gson.annotations.SerializedName

data class BookDetailsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("description")
    val description: String
) {
    fun getBookModel() = Book(
        title = this.title,
        description = this.description,
        author = this.author,
    )
}
