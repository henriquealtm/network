package br.com.henriquealtmayer.network.list.livedata.data.api

import br.com.henriquealtmayer.network.list.livedata.data.model.HeroListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LdApi {

    @GET("/v1/public/characters")
    fun getHeroListAsync(@Query("offset") offset: Int): Deferred<Response<HeroListResponse>>

}