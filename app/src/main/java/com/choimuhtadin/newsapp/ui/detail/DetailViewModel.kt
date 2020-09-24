package com.choimuhtadin.newsapp.ui.detail

import android.graphics.Bitmap
import android.util.Log
import android.webkit.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.choimuhtadin.newsapp.data.remote.helper.NetworkState
import com.choimuhtadin.newsapp.ui.base.BaseViewModel
import javax.inject.Inject


class DetailViewModel @Inject constructor(): BaseViewModel() {

    private val TAG = "DetailViewModel"

    private val _urlWeb = MutableLiveData<String>()
    val urlWeb: LiveData<String> get() = _urlWeb

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    private var hasLoaded:Boolean = false

    fun loadMenu(url: String, webView:WebView){

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                _progress.postValue(newProgress)
            }
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                networkState(NetworkState.LOADING)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                networkState(NetworkState.LOADED)
                super.onPageFinished(view, url)
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                if(networkState.value != NetworkState.LOADED)
                    handleError(Throwable(errorResponse.toString()))
            }
        }

        webView.loadUrl(url)
    }
}