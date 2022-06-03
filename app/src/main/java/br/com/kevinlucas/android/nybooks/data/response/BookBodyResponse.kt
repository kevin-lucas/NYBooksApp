package br.com.kevinlucas.android.nybooks.data.response

import com.google.gson.annotations.SerializedName

data class BookBodyResponse(
    @SerializedName("results")
    val booksResults: List<BookResultsResponse>
)