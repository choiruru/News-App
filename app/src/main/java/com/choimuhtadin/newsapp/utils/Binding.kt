package com.choimuhtadin.newsapp.utils

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.choimuhtadin.newsapp.R


@BindingAdapter("imgUrl")
fun AppCompatImageView.bindImgUrl(url:String?){
    if(url!=null){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img_placeholder_news)
            .error(R.drawable.img_placeholder_news)
            .into(this)
    }else{
        setImageResource(R.drawable.img_placeholder_news)
    }
}

@BindingAdapter("loadWeb")
fun WebView.loadWeb(url:String){
    this.loadUrl(url)
}

@BindingAdapter("setWebViewClient")
fun WebView.setWebViewClient(client: WebViewClient?) {
    this.webViewClient = client
}