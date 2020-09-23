package com.choimuhtadin.newsapp.di.component

import android.app.Application
import com.choimuhtadin.newsapp.NewsApp
import com.choimuhtadin.newsapp.di.module.*
import com.choimuhtadin.newsapp.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication?> {

    fun inject(application: NewsApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}