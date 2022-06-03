package br.com.kevinlucas.android.nybooks.data

import br.com.kevinlucas.android.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "17e1M0SBPhVdgwskXzvmSuAGmPdZmZEA",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<List<BookBodyResponse>>
}