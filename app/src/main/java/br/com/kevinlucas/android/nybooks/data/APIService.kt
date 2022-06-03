package br.com.kevinlucas.android.nybooks.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


object APIService {

    private fun initRetrofit () : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")

            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service = initRetrofit().create(NYTServices::class.java)
}