package br.com.henriquealtmayer.network.list.livedata.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.henriquealtmayer.network.list.livedata.data.api.LdApi
import br.com.henriquealtmayer.network.list.livedata.data.model.HeroResponse
import br.com.henriquealtmayer.network.list.livedata.domain.repository.ILdRepository
import br.com.henriquealtmayer.network.structure.livedata.Resource
import br.com.henriquealtmayer.network.structure.livedata.makeCall

class LdRepository(
    private val api: LdApi
) : ILdRepository {

    override fun getList(offset: Int): LiveData<Resource<List<HeroResponse>>> =
        Transformations.map(makeCall(api.getHeroListAsync(offset))) { resource ->
            resource.mapResource { resource.data?.data?.results }
        }

}