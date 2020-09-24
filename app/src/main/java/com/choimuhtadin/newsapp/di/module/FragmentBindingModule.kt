package com.choimuhtadin.newsapp.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.choimuhtadin.newsapp.di.scope.FragmentKey
import com.choimuhtadin.newsapp.di.module.factory.InjectingFragmentFactory
import com.choimuhtadin.newsapp.ui.detail.DetailFragment
import com.choimuhtadin.newsapp.ui.main.MainFragment
import com.choimuhtadin.newsapp.ui.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(SplashFragment::class)
    abstract fun bindSplashFragment(splashFragment: SplashFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    abstract fun bindMainFragment(mainFragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(detailFragment: DetailFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}