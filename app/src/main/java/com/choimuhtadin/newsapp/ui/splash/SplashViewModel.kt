package com.choimuhtadin.newsapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.choimuhtadin.newsapp.ui.base.BaseViewModel
import com.choimuhtadin.newsapp.utils.EspressoIdlingResource
import com.choimuhtadin.newsapp.utils.SingleEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val _complete = MutableLiveData<SingleEvents<Boolean>>()

    val complete : LiveData<SingleEvents<Boolean>>
        get() = _complete

    lateinit var disposable:Disposable

    fun countSplash(){
        disposable = Observable.timer(1, TimeUnit.SECONDS)
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if(!EspressoIdlingResource.getIdlingResource().isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }
            .subscribe{
                _complete.value = SingleEvents(true)
            }
    }

    fun dispose(){
        disposable.dispose()
    }
}