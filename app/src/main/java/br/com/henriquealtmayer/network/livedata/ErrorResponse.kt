package br.com.henriquealtmayer.network.livedata

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val httpMessage: String?
)