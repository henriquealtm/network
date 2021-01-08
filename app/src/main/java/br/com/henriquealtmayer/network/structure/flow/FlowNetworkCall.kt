package br.com.henriquealtmayer.network.structure.flow

import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.structure.httpNetworkError
import br.com.henriquealtmayer.network.commons.structure.ResultError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <RESPONSE : Any> makeCall(
    call: suspend () -> RESPONSE
): Flow<Resource<RESPONSE>> =
    flow {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(call.invoke()))
        } catch (exception: Exception) {
            val networkError = when (exception) {
                is ConnectException, is UnknownHostException -> ResultError.ConnectionError
                is HttpException -> httpNetworkError(exception)
                is SocketTimeoutException -> ResultError.TimeOutError
                else -> ResultError.FailureError
            }
            emit(Resource.Error<RESPONSE>(networkError))
        }
    }