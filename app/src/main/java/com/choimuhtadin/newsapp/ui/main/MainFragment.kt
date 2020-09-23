package com.choimuhtadin.newsapp.ui.main

import android.os.Bundle
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.databinding.FragmentMainBinding
import com.choimuhtadin.newsapp.ui.base.BaseFragment
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}