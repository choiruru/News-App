package com.choimuhtadin.newsapp.data.remote.api

import com.choimuhtadin.newsapp.data.remote.model.News
import com.choimuhtadin.newsapp.data.remote.model.Sources
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("sources")
    fun getSources(
        @Query("apiKey") apiKey:String
    ): Single<Sources>

    @GET("everything")
    fun getNews(
        @Query("q") query:String,
        @Query("sources") idSource:String,
        @Query("apiKey") apiKey:String,
        @Query("pageSize") pageSize:String,
        @Query("page") page:String
    ): Single<News>
}