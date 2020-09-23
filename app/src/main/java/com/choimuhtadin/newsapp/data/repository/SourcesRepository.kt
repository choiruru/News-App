package com.choimuhtadin.newsapp.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.choimuhtadin.newsapp.BuildConfig
import com.choimuhtadin.newsapp.data.remote.api.NewsApi
import com.choimuhtadin.newsapp.data.remote.helper.ErrorNetworkHandler
import com.choimuhtadin.newsapp.data.remote.model.Sources
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

interface SourcesRepository {
    fun getSources(): Single<Sources>
}

class SourcesRepositoryImpl @Inject constructor(private val service: NewsApi): SourcesRepository, BaseRepository() {

    override fun getSources(): Single<Sources> {

        return composeSingle { service.getSources(BuildConfig.API_KEY) }
            .compose(ErrorNetworkHandler())
    }
}