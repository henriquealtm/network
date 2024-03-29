package br.com.henriquealtmayer.network.commons.structure

sealed class Resource<out T>(val data: T? = null) {

    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(val errorData: ResultError) : Resource<T>()
    class Loading<T> : Resource<T>()

    fun <R> mapResource(onSuccess: (T?) -> R?): Resource<R> = when (this) {
        is Success -> Success(onSuccess(data))
        is Error -> Error(errorData)
        is Loading -> Loading()
    }

}
