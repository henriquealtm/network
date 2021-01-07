package br.com.henriquealtmayer.network.list.livedata.data.model

data class HeroListData(
    val results: List<HeroResponse>
)

data class HeroListResponse(
    val data: HeroListData
)