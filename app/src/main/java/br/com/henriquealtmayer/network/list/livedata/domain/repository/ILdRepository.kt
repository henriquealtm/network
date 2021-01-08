package br.com.henriquealtmayer.network.list.livedata.domain.repository

import androidx.lifecycle.LiveData
import br.com.henriquealtmayer.network.commons.model.data.HeroResponse
import br.com.henriquealtmayer.network.commons.structure.Resource

interface ILdRepository {

    fun getList(offset: Int): LiveData<Resource<List<HeroResponse>>>

}