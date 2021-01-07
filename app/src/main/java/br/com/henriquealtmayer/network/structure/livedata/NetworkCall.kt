package br.com.henriquealtmayer.network.structure.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.henriquealtmayer.network.commons.handleOptional
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Suppress("UNCHECKED_CAST")
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

private fun httpNetworkError(
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