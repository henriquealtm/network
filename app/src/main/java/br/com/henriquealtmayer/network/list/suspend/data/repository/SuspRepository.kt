package br.com.henriquealtmayer.network.list.suspend.data.repository

import br.com.henriquealtmayer.network.list.suspend.data.api.SuspApi
import br.com.henriquealtmayer.network.list.suspend.domain.repository.ISuspRepository
import br.com.henriquealtmayer.network.structure.suspend.makeCall

class SuspRepository(
    private val suspApi: SuspApi
) : ISuspRepository {

    override suspend fun getList(offset: Int) = makeCall {
        suspApi.getHeroListAsync(offset).data.results
    }

}