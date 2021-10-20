package com.developer.anishakd4.byjusassignment.Networking

import com.developer.anishakd4.byjusassignment.Model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface GetNewsInterface {

    @GET("/v2/everything?q=bitcoin&from=2019-12-23&sortBy=publishedAt&apiKey=c3e47d5b86c04b6386bf0fb1a11a4853")
    suspend fun getNews(): Response<NewsModel>
}