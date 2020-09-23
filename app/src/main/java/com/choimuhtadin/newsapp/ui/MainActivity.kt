package com.choimuhtadin.newsapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.databinding.ActivityMainBinding
import com.choimuhtadin.newsapp.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding> (){

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
}