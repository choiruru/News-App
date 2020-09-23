package com.choimuhtadin.newsapp.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseRepository {

    protected fun <T> composeSingle(task: () -> Single<T>): Single<T> = task()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}