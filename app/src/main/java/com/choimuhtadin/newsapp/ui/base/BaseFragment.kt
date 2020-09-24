package com.choimuhtadin.newsapp.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {
    private val TAG = "BaseFragment"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewDataBinding: B
    lateinit var mViewModel: V

    val binding: B get() = mViewDataBinding
    val viewModel: V get() = mViewModel

    protected abstract fun getViewModelClass(): Class<V>

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ");
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()

        super.onCreateView(inflater, container, savedInstanceState)

        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady(savedInstanceState)
    }

    fun waitForTransition(view:View){
        postponeEnterTransition()
        view.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }
}