package br.com.henriquealtmayer.network.list.suspend.data.repository

import br.com.henriquealtmayer.network.commons.model.data.HeroResponse
import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.list.suspend.data.api.SuspApi
import br.com.henriquealtmayer.network.list.suspend.domain.repository.ISuspRepository
import kotlinx.coroutines.delay

class SuspRepository(
    private val suspApi: SuspApi
) : ISuspRepository {

//    override suspend fun getList(offset: Int) =
//        makeCall { suspApi.getHeroListAsync(offset).data.results }


    override suspend fun getList(offset: Int): Resource<List<HeroResponse>> {
        delay(3000)

        return Resource.Success(
            listOf(
                HeroResponse(123, "1111", "123123123123", null)
            )
        )
    }


}