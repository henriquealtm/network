package br.com.henriquealtmayer.network.commons.activity

import androidx.lifecycle.*
import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.model.domain.Hero
import br.com.henriquealtmayer.network.commons.structure.Resource

abstract class BaseListViewModel(

) : ViewModel() {

    protected var offset = 0

    protected val resource = MutableLiveData<Resource<List<Hero>>>()

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

    abstract fun getList()

}