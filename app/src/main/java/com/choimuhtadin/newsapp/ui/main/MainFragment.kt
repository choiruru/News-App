package com.choimuhtadin.newsapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.data.remote.model.Article
import com.choimuhtadin.newsapp.databinding.FragmentMainBinding
import com.choimuhtadin.newsapp.ui.MainActivity
import com.choimuhtadin.newsapp.ui.base.BaseFragment
import com.choimuhtadin.newsapp.utils.OnScrollListener
import com.ferfalk.simplesearchview.SimpleSearchView
import javax.inject.Inject


class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding, MainViewModel>(),
    MenuAdapter.OnMenuItemClickListener{

    private val TAG = "MainFragment"
    private lateinit var adapter:MenuAdapter
    private lateinit var articleAdapter : ArticleAdapter
    private lateinit var onScrollListener: OnScrollListener

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

        binding.viewModel = viewModel

        initToolbar()
        initMenuRecyclerview()
        initArticleRecyclerview()
        initErrorView()

        if(!isFragmentFromPaused){
            viewModel.loadSources()
        }
    }

    private fun initToolbar(){
        binding.content.toolbar.setNavigationOnClickListener(View.OnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        })
        binding.content.searchView.setMenuItem(binding.content.toolbar.menu.getItem(0))
        viewModel.source.observe(this, Observer {
            binding.content.toolbar.title = it.name
        })

        binding.content.searchView.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchArticles(it)
                    onScrollListener.reset()
                }
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchArticles(it)
                    onScrollListener.reset()
                }
                return true;
            }

            override fun onQueryTextCleared(): Boolean {
                viewModel.searchArticles("")
                onScrollListener.reset()
                return true;
            }
        })

    }

    private fun initArticleRecyclerview(){
        onScrollListener = OnScrollListener(binding.content.recyclerview.layoutManager as LinearLayoutManager) {
            viewModel.loadMore()
        }

        articleAdapter = ArticleAdapter(object : ArticleAdapter.OnArticleItemClickListener {
            override fun onClick(article: Article) {
//                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(
//                    article.url
//                )
//                findNavController()
//                    .navigate(
//                        action,
//                        FragmentNavigator.Extras.Builder()
//                            .build()
//                    )
            }
        })
        binding.content.recyclerview.adapter = articleAdapter
        binding.content.recyclerview.addOnScrollListener(onScrollListener)

        viewModel.article.observe(this, Observer {
            Log.d(TAG, "size: " + it.size);
            articleAdapter.submitList(it)
        })
    }

    private fun initMenuRecyclerview() {
        adapter = MenuAdapter(this)
        binding.rcMenu.adapter = adapter

        viewModel.sources.observe(this, Observer {
            adapter.submitList(it)
            if (!isFragmentFromPaused) {
                viewModel.loadNews(0)
            }
        })

        binding.edtSearchSources.addTextChangedListener { text: Editable? ->
            viewModel.filterSources(text.toString())
        }
    }

    override fun onClick(index: Int) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        onScrollListener.reset()
        viewModel.loadNews(index)
    }

    private fun initErrorView(){
        binding.content.lytOffline.btnRetry.setOnClickListener {
            onScrollListener.reset()
            viewModel.retry()
        }
    }
}