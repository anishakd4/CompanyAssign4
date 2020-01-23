package com.developer.anishakd4.byjusassignment.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetNewsService {

    private val BASE_URL = "https://newsapi.org";

    fun getGetDataService(): GetNewsInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetNewsInterface::class.java)
    }

}