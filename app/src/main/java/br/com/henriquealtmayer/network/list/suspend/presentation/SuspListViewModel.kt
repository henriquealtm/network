package br.com.henriquealtmayer.network.list.suspend.presentation

import androidx.lifecycle.*
import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.model.domain.Hero
import br.com.henriquealtmayer.network.list.suspend.domain.usecase.SuspListUseCase

import kotlinx.coroutines.launch

class SuspListViewModel(
    private val listUseCase: SuspListUseCase
) : ViewModel() {

    private var offset = 0

    val resource = MutableLiveData<Resource<List<Hero>>>()

    private fun getList() {
        resource.value = Resource.Loading()

        viewModelScope.launch {
            resource.value = listUseCase.getList(offset)
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