package br.com.henriquealtmayer.network.commons.structure

import java.io.Serializable

sealed class ResultError : Serializable {

    object ConnectionError : ResultError()

    object ExpiredSessionError : ResultError()

    object TimeOutError : ResultError()

    object FailureError : ResultError()

    class ResponseError(
        val httpCode: Int,
        val httpMessage: String
    ) : ResultError()

}
