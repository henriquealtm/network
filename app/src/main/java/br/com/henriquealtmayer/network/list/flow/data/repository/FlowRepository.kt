package br.com.henriquealtmayer.network.list.flow.data.repository

import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.list.flow.domain.repository.IFlowRepository
import br.com.henriquealtmayer.network.commons.model.data.HeroResponse
import br.com.henriquealtmayer.network.list.flow.data.api.FlowApi
import br.com.henriquealtmayer.network.structure.flow.makeCall
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowRepository(
    private val flowApi: FlowApi
) : IFlowRepository {

//    override suspend fun getList(offset: Int): Flow<Resource<List<HeroResponse>>> = makeCall {
//        flowApi.getHeroListAsync(offset).data.results
//    }

    override suspend fun getList(offset: Int): Flow<Resource<List<HeroResponse>>> = flow {
        emit(Resource.Loading())

        delay(3000)

        emit(
            Resource.Success(
                listOf(
                    HeroResponse(123, "1111", "123123123123", null)
                )
            )
        )
    }

}