package com.choimuhtadin.newsapp.di.module

import com.choimuhtadin.newsapp.di.scope.ApplicationScope
import com.choimuhtadin.newsapp.utils.AppSchedulerProvider
import com.choimuhtadin.newsapp.utils.SchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}