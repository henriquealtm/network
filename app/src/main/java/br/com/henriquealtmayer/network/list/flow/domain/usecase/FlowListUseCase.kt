package br.com.henriquealtmayer.network.list.flow.domain.usecase

import br.com.henriquealtmayer.network.commons.map.toHeroList
import br.com.henriquealtmayer.network.list.flow.domain.repository.IFlowRepository
import kotlinx.coroutines.flow.map

class FlowListUseCase(
    private val flowRepository: IFlowRepository
) {

    suspend fun getList(
        offset: Int
    ) = flowRepository.getList(offset).map { resource ->
        resource.mapResource { list ->
            list?.toHeroList()
        }
    }

}