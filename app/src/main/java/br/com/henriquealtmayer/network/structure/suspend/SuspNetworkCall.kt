package br.com.henriquealtmayer.network.structure.suspend

import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.structure.httpNetworkError
import br.com.henriquealtmayer.network.commons.structure.ResultError
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <RESPONSE : Any> makeCall(
    call: suspend () -> RESPONSE
): Resource<RESPONSE> = try {
    Resource.Success(call.invoke())
} catch (exception: Exception) {
    val networkError = when (exception) {
        is ConnectException, is UnknownHostException -> ResultError.ConnectionError
        is HttpException -> httpNetworkError(exception)
        is SocketTimeoutException -> ResultError.TimeOutError
        else -> ResultError.FailureError
    }
    Resource.Error(networkError)
}