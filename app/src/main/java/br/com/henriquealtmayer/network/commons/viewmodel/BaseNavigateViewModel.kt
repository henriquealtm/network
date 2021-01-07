package br.com.henriquealtmayer.network.commons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.henriquealtmayer.network.commons.livedata.SingleLiveEvent

abstract class BaseNavigateViewModel : ViewModel() {

    // Navigate back - Section
    private val mNavigateBack = SingleLiveEvent<Boolean>().apply { value = false }
    val navigateBack: LiveData<Boolean> = mNavigateBack
    fun navigateBackClick() {
        mNavigateBack.value = true
    }

}