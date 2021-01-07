package br.com.henriquealtmayer.network.structure.livedata

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(
    getUserToken: () -> String,
    baseUrl: String
): Retrofit = retrofitClient(
    httpClient(getUserToken),
    gsonConverter(),
    baseUrl
)

private fun gsonConverter() = GsonConverterFactory.create(
    GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
)

private fun httpClient(
    getUserToken: () -> String
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor { chain ->
            val builder = chain.request()
                .newBuilder()
                .addHeader("token", getUserToken.invoke())
            chain.proceed(builder.build())
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

private fun retrofitClient(
    httpClient: OkHttpClient,
    gsonConverter: GsonConverterFactory,
    baseUrl: String
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(httpClient)
    .addConverterFactory(gsonConverter)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()