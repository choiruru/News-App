package com.choimuhtadin.newsapp.di.module

import com.choimuhtadin.newsapp.data.remote.api.NewsApi
import com.choimuhtadin.newsapp.data.repository.NewsRepository
import com.choimuhtadin.newsapp.data.repository.NewsRepositoryImpl
import com.choimuhtadin.newsapp.data.repository.SourcesRepository
import com.choimuhtadin.newsapp.data.repository.SourcesRepositoryImpl
import com.choimuhtadin.newsapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideSourcesRepository(service:NewsApi): SourcesRepository {
        return SourcesRepositoryImpl(service)
    }

    @Provides
    @ApplicationScope
    fun provideNewsRepository(service:NewsApi): NewsRepository {
        return NewsRepositoryImpl(service)
    }
}