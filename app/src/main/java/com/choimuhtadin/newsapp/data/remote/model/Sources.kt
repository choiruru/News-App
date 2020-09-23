package com.choimuhtadin.newsapp.data.remote.model

data class Sources(
    val sources: List<Source>,
    val status: String
)

data class Source(
    val id: String,
    val name: String
)