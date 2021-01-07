package br.com.henriquealtmayer.network.structure.livedata

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val httpMessage: String?
)