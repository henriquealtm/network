package br.com.henriquealtmayer.network.list.suspend.presentation

import br.com.henriquealtmayer.network.commons.activity.BaseListActivity
import br.com.henriquealtmayer.network.commons.activity.BaseListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SuspListActivity : BaseListActivity() {

    override val listViewModel: SuspListViewModel by viewModel()
}