package com.choimuhtadin.newsapp.ui.base

interface BaseModel {

    fun id(): Long = hashCode().toLong()
    fun areContentsTheSame(newItem: BaseModel): Boolean = toString() == newItem.toString()
}