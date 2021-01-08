package br.com.henriquealtmayer.network.list.suspend.domain.repository

import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.model.data.HeroResponse

interface ISuspRepository {

    suspend fun getList(offset: Int): Resource<List<HeroResponse>>

}