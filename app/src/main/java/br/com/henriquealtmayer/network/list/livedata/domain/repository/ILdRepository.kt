package br.com.henriquealtmayer.network.list.livedata.domain.repository

import androidx.lifecycle.LiveData
import br.com.henriquealtmayer.network.list.livedata.data.model.HeroResponse
import br.com.henriquealtmayer.network.structure.livedata.Resource

interface ILdRepository {

    fun getList(offset: Int): LiveData<Resource<List<HeroResponse>>>

}