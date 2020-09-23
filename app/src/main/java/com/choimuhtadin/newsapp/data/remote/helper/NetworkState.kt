package com.choimuhtadin.newsapp.data.remote.helper

data class NetworkState constructor(
    val status: Status,
    val failure: Failure? = null
) {
    companion object {
        val LOADED =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.LOADING)
        val FAILED = NetworkState(Status.FAILED)

        fun error(failure: Failure) = NetworkState(
            Status.FAILED,
            failure
        )
    }
}
