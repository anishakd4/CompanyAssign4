package com.developer.anishakd4.byjusassignment.Model

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticlesModel>
)