package br.com.henriquealtmayer.network.di

import br.com.henriquealtmayer.network.BuildConfig
import br.com.henriquealtmayer.network.commons.extension.toMD5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL_NAME = "BASE_URL"
private const val BASE_URL = "https://gateway.marvel.com"
private const val PRIVATE_KEY_NAME = "PRIVATE_KEY"
private const val PRIVATE_KEY = "60fad1c4f98d88c33dc165220d948dec0c6d1e6a"
private const val PUBLIC_KEY_NAME = "PUBLIC_KEY"
private const val PUBLIC_KEY = "17509291fec39c09ec90a0d194be6d6f"

val commonsModule = module {
    // Marvel
    single(named(PRIVATE_KEY_NAME)) { PRIVATE_KEY }
    single(named(PUBLIC_KEY_NAME)) { PUBLIC_KEY }

    // Retrofit client
    single(named(BASE_URL_NAME)) { BASE_URL }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val privateKey = get<String>(named(PRIVATE_KEY_NAME))
        val publicKey = get<String>(named(PUBLIC_KEY_NAME))

        Interceptor { chain ->
            val currentTimestamp = System.currentTimeMillis()
            val hash = "$currentTimestamp$privateKey$publicKey".toMD5()

            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("ts", currentTimestamp.toString())
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("hash", hash)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
    }

    single {
        val client = OkHttpClient().newBuilder()

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }

        client.addInterceptor(get())

        client.build()
    }
}