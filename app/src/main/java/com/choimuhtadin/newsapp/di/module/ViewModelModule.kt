package com.choimuhtadin.newsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choimuhtadin.newsapp.di.scope.ApplicationScope
import com.choimuhtadin.newsapp.di.scope.ViewModelKey
import com.choimuhtadin.newsapp.di.module.factory.ViewModelFactory
import com.choimuhtadin.newsapp.ui.main.MainViewModel
import com.choimuhtadin.newsapp.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun providesSplashViewModel(viewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(viewModel: MainViewModel) : ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel::class)
//    internal abstract fun providesDetailViewModel(viewModel: DetailViewModel) : ViewModel

}