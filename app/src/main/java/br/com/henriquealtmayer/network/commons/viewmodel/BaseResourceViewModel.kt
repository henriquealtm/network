package br.com.henriquealtmayer.network.commons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.henriquealtmayer.network.commons.structure.Resource

abstract class BaseResourceViewModel<T> : BaseNavigateViewModel() {

    abstract val getResourceCall: () -> LiveData<Resource<T>>

    private val mGetResource = MutableLiveData(false)
    val getResource: LiveData<Boolean> = mGetResource
    fun getResource() {
        mGetResource.value = true
    }

    private val resource: LiveData<Resource<T>> =
        Transformations.switchMap(mGetResource) { mustGetKeyList ->
            mustGetKeyList?.takeIf { it }?.let {
                getResourceCall()
            }
        }

    val showLoader: LiveData<Boolean> = Transformations.map(resource) { resource ->
        resource is Resource.Loading
    }

    val showError: LiveData<Boolean> = Transformations.map(resource) { resource ->
        resource is Resource.Error
    }

    val showSuccessContent: LiveData<Boolean> =
        Transformations.map(resource) { resource ->
            resource is Resource.Success
        }

    val successResource: LiveData<T> =
        Transformations.map(resource) { resource ->
            resource.data
        }

    fun tryAgain() {
        getResource()
    }

}