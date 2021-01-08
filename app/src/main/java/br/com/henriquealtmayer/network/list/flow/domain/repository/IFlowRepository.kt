package br.com.henriquealtmayer.network.list.flow.domain.repository

import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.model.data.HeroResponse
import kotlinx.coroutines.flow.Flow

interface IFlowRepository {

    suspend fun getList(offset: Int): Flow<Resource<List<HeroResponse>>>

}