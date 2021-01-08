package br.com.henriquealtmayer.network.list.flow.presentation

import androidx.lifecycle.*
import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.model.domain.Hero
import br.com.henriquealtmayer.network.list.flow.domain.usecase.FlowListUseCase
import br.com.henriquealtmayer.network.list.suspend.domain.usecase.SuspListUseCase
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

class FlowListViewModel(
    private val listUseCase: FlowListUseCase
) : ViewModel() {

    private var offset = 0

    private val resource = MutableLiveData<Resource<List<Hero>>>()

    private fun getList() {
        viewModelScope.launch {
            listUseCase.getList(offset).collect {
                resource.value = it
            }
        }
    }

    val showLoader: LiveData<Boolean?> = Transformations.map(resource) { resource ->
        resource?.let { resource is Resource.Loading<*> }
    }

    private val mHeroList = MediatorLiveData<List<Hero>>().apply {
        addSource(resource) { resource ->
            resource.data?.let { list ->
                offset += list.size

                value = value?.toMutableList()?.apply {
                    addAll(list)
                    sortBy { it.name }
                }
            }
        }

        value = listOf()
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
        getList()
    }

    init {
        getList()
    }

}