package com.choimuhtadin.newsapp

import android.content.Context
import androidx.multidex.MultiDex
import com.choimuhtadin.newsapp.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class NewsApp : DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector() = applicationInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }
}