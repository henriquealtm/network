package br.com.henriquealtmayer.network.structure.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.structure.ResultError
import br.com.henriquealtmayer.network.commons.structure.httpNetworkError
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <RESPONSE : Any> makeCall(
    client: Deferred<Response<RESPONSE>>
): LiveData<Resource<RESPONSE>> {
    val result = MutableLiveData<Resource<RESPONSE>>()
    result.value = Resource.Loading()

    GlobalScope.launch {
        try {
            val response = client.awaitResult().getOrThrow()
            result.postValue(Resource.Success(response))
        } catch (exception: Exception) {
            val networkError = when (exception) {
                is ConnectException, is UnknownHostException -> ResultError.ConnectionError
                is HttpException -> httpNetworkError(exception)
                is SocketTimeoutException -> ResultError.TimeOutError
                else -> ResultError.FailureError
            }
            result.postValue(Resource.Error(networkError))

            exception.printStackTrace()
        }
    }

    return result
}