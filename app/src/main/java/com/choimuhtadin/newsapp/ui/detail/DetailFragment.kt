package com.choimuhtadin.newsapp.ui.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.databinding.FragmentDetailBinding
import com.choimuhtadin.newsapp.ui.MainActivity
import com.choimuhtadin.newsapp.ui.base.BaseFragment
import javax.inject.Inject

class DetailFragment @Inject constructor() : BaseFragment<FragmentDetailBinding, DetailViewModel>(){

    private val TAG = "MainFragment"
    private val args : DetailFragmentArgs by navArgs()

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {

        binding.viewModel = viewModel
        load()

        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        binding.lytOffline.btnRetry.setOnClickListener {
            load()
        }
    }

    private fun load(){
        viewModel.loadMenu(args.urlWeb, binding.webView)
    }
}