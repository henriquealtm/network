package br.com.henriquealtmayer.network.list.livedata.data.model

import br.com.henriquealtmayer.network.commons.model.data.HeroResponse

data class HeroListData(
    val results: List<HeroResponse>
)

data class HeroListResponse(
    val data: HeroListData
)