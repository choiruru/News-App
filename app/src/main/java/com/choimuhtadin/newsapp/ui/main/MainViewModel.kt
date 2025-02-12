package com.choimuhtadin.newsapp.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.choimuhtadin.newsapp.data.remote.helper.DataStatus
import com.choimuhtadin.newsapp.data.remote.helper.NetworkState
import com.choimuhtadin.newsapp.data.remote.model.Source
import com.choimuhtadin.newsapp.data.repository.NewsRepository
import com.choimuhtadin.newsapp.data.repository.SourcesRepository
import com.choimuhtadin.newsapp.ui.base.BaseModel
import com.choimuhtadin.newsapp.ui.base.BaseViewModel
import com.choimuhtadin.newsapp.ui.base.ItemLoading
import com.choimuhtadin.newsapp.utils.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sourcesRepository: SourcesRepository,
    private val newsRepository: NewsRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {

    private val TAG = "MainViewModel"

    private val _sourcesFull = MutableLiveData<List<Source>>()
    private val _sources = MutableLiveData<List<Source>>()
    val sources: LiveData<List<Source>> get() = _sources

    private val _article = MutableLiveData<List<BaseModel>>()
    val article: LiveData<List<BaseModel>> get() = _article

    private val _dataStatus = MutableLiveData<DataStatus>()
    val dataStatus: LiveData<DataStatus> get() = _dataStatus

    private val _source = MutableLiveData<Source>()
    val source: LiveData<Source> get() = _source

    private var page:Int=1
    private var totalResults = 0
    private var query=""
    private var onPause=false;

    fun loadSources(){
        _sourcesFull.value?.run {
            return
        }

        networkState(NetworkState.LOADING)
        lastDisposable = sourcesRepository.getSources()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({ data ->
                _sourcesFull.postValue(data.sources)
                _sources.postValue(data.sources)
                networkState(NetworkState.LOADED)
            },{
                handleError(it)
            })

        lastDisposable?.let { compositeDisposable.add(it) }
    }

    fun filterSources(query:String){
       query.let {
           val data = _sourcesFull.value?.filter { item -> item.name.toLowerCase().contains(query.toLowerCase()) }
           _sources.postValue(data)
       }
    }

    fun loadNews(index:Int){
        sources.value?.let {
            if(it.isNotEmpty()){
                _source.value = it[index]
                retry()
            }else{
                _dataStatus.postValue(DataStatus.EMPTY)
            }
        }
    }

    fun retry(){
        networkState(NetworkState.LOADING)
        page=1
        _article.postValue(mutableListOf())
        getNews()
    }

    fun searchArticles(query:String){
        this.query = query
        retry()
    }

    fun loadMore(){
        page++
        getNews()
    }

    private fun getNews(){
        source.value?.let { it ->
            lastDisposable?.dispose()
            lastDisposable = newsRepository.getNews(query, it.id , page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ models ->
                    totalResults = models.totalResults
                    if(models.totalResults>0){

                        val temp = _article.value?.toMutableList() ?: mutableListOf()
                        temp.removeAll { it is ItemLoading }

                        temp.addAll(models.articles)

                        if(totalResults>temp.size){
                            temp.add(ItemLoading(""))
                        }

                        _article.postValue(temp)

                        _dataStatus.postValue(DataStatus.NOT_EMPTY)
                    }else{
                        _dataStatus.postValue(DataStatus.EMPTY)
                    }
                    networkState(NetworkState.LOADED)
                },{
                    handleError(it)
                })

            lastDisposable?.let { compositeDisposable.add(it) }
        }
    }

    fun setPause(state:Boolean){
        this.onPause=state
    }

    fun isFromPause():Boolean{
        return onPause
    }

}