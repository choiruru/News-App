package com.choimuhtadin.newsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.data.remote.model.Article
import com.choimuhtadin.newsapp.databinding.ItemArticleBinding
import com.choimuhtadin.newsapp.databinding.ItemLoadMoreBinding
import com.choimuhtadin.newsapp.ui.base.BaseModel

import javax.inject.Inject

class ArticleAdapter @Inject constructor( private val onItemClick : OnArticleItemClickListener) :
    ListAdapter<BaseModel,  RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder(
            DiffItemArticle
        ).build()
    ) {
    private val TAG = "SearchJokeAdapter"

    private val POST_TYPE_ARTICLE = 1
    private val POST_TYPE_LOADING = 2

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position) is Article)POST_TYPE_ARTICLE else POST_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        if(viewType == POST_TYPE_ARTICLE){
            val binding = DataBindingUtil.inflate<ItemArticleBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_article,
                parent,
                false
            )
            return ArticleViewHolder(binding)
        }else{
            val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_load_more,
                parent,
                false
            )
            return LoadingViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == POST_TYPE_ARTICLE){
            val vHolder = holder as ArticleViewHolder
            vHolder.bind(getItem(position) as Article)
            vHolder.binding.root.setOnClickListener {
                onItemClick.onClick(getItem(position) as Article)
            }
        }else{
            val vHolder = holder as LoadingViewHolder
            vHolder.bind()
        }
    }

    class ArticleViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Article) {
            binding.article = item
            binding.executePendingBindings()
        }
    }

    class LoadingViewHolder(var binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.executePendingBindings()
        }
    }

    interface OnArticleItemClickListener {
        fun onClick(article: Article)
    }
}

object DiffItemArticle : DiffUtil.ItemCallback<BaseModel>(){
    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

}