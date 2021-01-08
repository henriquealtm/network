package br.com.henriquealtmayer.network.list.flow.data.api

import br.com.henriquealtmayer.network.list.livedata.data.model.HeroListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlowApi {

    @GET("/v1/public/characters")
    suspend fun getHeroListAsync(@Query("offset") offset: Int): HeroListResponse

}