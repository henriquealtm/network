package br.com.henriquealtmayer.network.commons.structure

import br.com.henriquealtmayer.network.commons.handleOptional
import com.google.gson.Gson
import retrofit2.HttpException

fun httpNetworkError(
    exception: HttpException
) = if (exception.code() == 401) {
    ResultError.ExpiredSessionError
} else {
    val apiError = try {
        Gson().fromJson(exception.response()?.errorBody()?.charStream(), ErrorResponse::class.java)
    } catch (e: Exception) {
        ErrorResponse("")
    }

    ResultError.ResponseError(
        httpCode = exception.code(),
        httpMessage = apiError?.httpMessage.handleOptional(),
    )
}