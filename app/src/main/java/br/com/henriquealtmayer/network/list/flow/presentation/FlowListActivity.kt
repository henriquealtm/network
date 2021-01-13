package br.com.henriquealtmayer.network.list.flow.presentation

import br.com.henriquealtmayer.network.commons.activity.BaseListActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FlowListActivity : BaseListActivity() {

    override val listViewModel: FlowListViewModel by viewModel()

}