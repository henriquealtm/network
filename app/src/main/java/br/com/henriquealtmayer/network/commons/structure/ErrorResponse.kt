package br.com.henriquealtmayer.network.commons.structure

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val httpMessage: String?
)