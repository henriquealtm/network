package br.com.henriquealtmayer.network.list.suspend.presentation

import androidx.lifecycle.*
import br.com.henriquealtmayer.network.commons.activity.BaseListViewModel
import br.com.henriquealtmayer.network.commons.structure.Resource
import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.model.domain.Hero
import br.com.henriquealtmayer.network.list.suspend.domain.usecase.SuspListUseCase

import kotlinx.coroutines.launch

class SuspListViewModel(
    private val listUseCase: SuspListUseCase
) : BaseListViewModel() {

    override fun getList() {
        resource.value = Resource.Loading()

        viewModelScope.launch {
            resource.value = listUseCase.getList(offset)
        }
    }

    init {
        getList()
    }

}