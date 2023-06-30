package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*object RetrofitInstance {
    val api : MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}*/
object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "69d66957eebff9666ea46bd464773cf0"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    lateinit var api: MovieApi
    fun initializeApi() {
        api = retrofit.create(MovieApi::class.java)
    }
}
