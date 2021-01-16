package br.com.henriquealtmayer.network.list.flow.data.repository

import br.com.henriquealtmayer.network.list.flow.data.api.FlowApi
import br.com.henriquealtmayer.network.list.flow.domain.repository.IFlowRepository
import br.com.henriquealtmayer.network.structure.flow.makeCall

class FlowRepository(
    private val flowApi: FlowApi
) : IFlowRepository {

    override suspend fun getList(offset: Int) = makeCall {
        flowApi.getHeroListAsync(offset).data.results
    }

}