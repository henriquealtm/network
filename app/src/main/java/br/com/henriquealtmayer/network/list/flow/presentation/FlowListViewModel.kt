package br.com.henriquealtmayer.network.list.flow.presentation

import androidx.lifecycle.viewModelScope
import br.com.henriquealtmayer.network.commons.activity.BaseListViewModel
import br.com.henriquealtmayer.network.list.flow.domain.usecase.FlowListUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowListViewModel(
    private val listUseCase: FlowListUseCase
) : BaseListViewModel() {

    override fun getList() {
        viewModelScope.launch {
            listUseCase.getList(offset).collect {
                resource.value = it
            }
        }
    }

    init {
        getList()
    }

}