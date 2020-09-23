package com.choimuhtadin.newsapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.databinding.FragmentSplashBinding
import com.choimuhtadin.newsapp.ui.base.BaseFragment
import javax.inject.Inject

class SplashFragment @Inject constructor() : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun onViewReady(savedInstance: Bundle?) {
        viewModel.countSplash()
        viewModel.complete.observe(this, Observer {
            val action = SplashFragmentDirections.actionToMainFragment()
            findNavController()
                .navigate(action,
                    FragmentNavigator.Extras.Builder()
                        .build())
        })
    }
}