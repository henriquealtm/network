package br.com.henriquealtmayer.network.list.suspend.domain.usecase

import br.com.henriquealtmayer.network.commons.map.toHeroList
import br.com.henriquealtmayer.network.list.suspend.domain.repository.ISuspRepository

class SuspListUseCase(
    private val suspRepository: ISuspRepository
) {

    suspend fun getList(offset: Int) = suspRepository.getList(offset).mapResource { list ->
        list?.toHeroList()
    }

}