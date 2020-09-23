package com.choimuhtadin.newsapp.data.repository

import com.choimuhtadin.newsapp.BuildConfig
import com.choimuhtadin.newsapp.data.remote.api.NewsApi
import com.choimuhtadin.newsapp.data.remote.helper.ErrorNetworkHandler
import com.choimuhtadin.newsapp.data.remote.model.News
import io.reactivex.Single
import javax.inject.Inject

interface NewsRepository {
    fun getNews(idSource:String,
                page:String): Single<News>
}

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApi
): NewsRepository, BaseRepository() {

    override fun getNews(
        idSource: String,
        page: String
    ): Single<News> {
        return composeSingle { service.getNews(idSource, BuildConfig.API_KEY, "10", page) }
            .compose(ErrorNetworkHandler())
    }

}