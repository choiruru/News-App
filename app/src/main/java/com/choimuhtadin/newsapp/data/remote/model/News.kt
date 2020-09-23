package com.choimuhtadin.newsapp.data.remote.model

import com.choimuhtadin.newsapp.ui.base.BaseModel

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : BaseModel