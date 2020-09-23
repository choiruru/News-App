package com.choimuhtadin.newsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.choimuhtadin.newsapp.R
import com.choimuhtadin.newsapp.data.remote.model.Source
import com.choimuhtadin.newsapp.databinding.ItemMenuBinding

import javax.inject.Inject

class MenuAdapter @Inject constructor(
    private val clickListener : OnMenuItemClickListener
) :
    ListAdapter<Source,  RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder(
            DiffItemBase
        ).build()
    ) {
    private val TAG = "MenuAdapter"

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMenuBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_menu,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder = holder as UserViewHolder
        vHolder.binding.lytMenuParent.setOnClickListener {
            clickListener.onClick(getItem(position))
        }
        vHolder.bind(getItem(position))
    }

    class UserViewHolder(var binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Source) {
            binding.country = item
            binding.executePendingBindings()
        }
    }

    interface OnMenuItemClickListener {
        fun onClick(country: Source)
    }
}

object DiffItemBase : DiffUtil.ItemCallback<Source>(){
    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem == newItem
    }

}