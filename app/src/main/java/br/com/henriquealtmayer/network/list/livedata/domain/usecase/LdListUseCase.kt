package br.com.henriquealtmayer.network.list.livedata.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.henriquealtmayer.network.list.livedata.domain.map.toHeroList
import br.com.henriquealtmayer.network.list.livedata.domain.model.Hero
import br.com.henriquealtmayer.network.list.livedata.domain.repository.ILdRepository
import br.com.henriquealtmayer.network.structure.livedata.Resource

class LdListUseCase(
    private val ldRepository: ILdRepository
) {

    operator fun invoke(
        offset: Int
    ): LiveData<Resource<List<Hero>>> =
        Transformations.map(ldRepository.getList(offset)) { resource ->
            resource.mapResource { list ->
                list?.toHeroList()
            }
        }

}