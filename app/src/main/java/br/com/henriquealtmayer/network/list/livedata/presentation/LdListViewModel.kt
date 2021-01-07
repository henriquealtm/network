package br.com.henriquealtmayer.network.list.livedata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.viewmodel.BaseResourceViewModel
import br.com.henriquealtmayer.network.list.livedata.domain.model.Hero
import br.com.henriquealtmayer.network.list.livedata.domain.usecase.LdListUseCase
import br.com.henriquealtmayer.network.structure.livedata.Resource

class LdListViewModel(
    private val listUseCase: LdListUseCase
) : BaseResourceViewModel<List<Hero>>() {

    override val getResourceCall: () -> LiveData<Resource<List<Hero>>> = {
        listUseCase(offset)
    }

    private var offset = 0

    private val mHeroList = MediatorLiveData<List<Hero>>().apply {
        addSource(successResource) { list ->
            list?.run {
                offset += list.size

                value = value?.toMutableList()?.apply {
                    addAll(list)
                    sortBy { it.name }
                }
            }
        }

        value = mutableListOf()
    }
    val heroList: LiveData<List<Hero>> = mHeroList

    val showHeroList: LiveData<Boolean?> = Transformations.map(heroList) { list ->
        list?.isNotEmpty()
    }

    val scrollToBottom: LiveData<Boolean> = Transformations.map(showLoader) { showLoader ->
        showLoader?.let { showLoader }
    }

    fun loadMoreHeroes() {
        if (showLoader.value.handleOptional()) {
            return
        }
        super.getResource()
    }

    init {
        super.getResource()
    }

}