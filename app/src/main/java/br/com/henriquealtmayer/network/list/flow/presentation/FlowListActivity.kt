package br.com.henriquealtmayer.network.list.flow.presentation

import br.com.henriquealtmayer.network.R
import br.com.henriquealtmayer.network.commons.activity.BaseActivity
import br.com.henriquealtmayer.network.commons.listadapter.ListAdapter
import br.com.henriquealtmayer.network.databinding.ActivityFlowListBinding
import kotlinx.android.synthetic.main.activity_ld_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class FlowListActivity : BaseActivity<ActivityFlowListBinding>(
    R.layout.activity_flow_list
) {

    private val flowListVm: FlowListViewModel by viewModel()

    override val loadVm: (ActivityFlowListBinding) -> Unit = { binding ->
        binding.vm = flowListVm
    }

    private lateinit var adapter: ListAdapter

    override fun initializeUI() {
        super.initializeUI()

        createListAdapter()
    }

    private fun createListAdapter() {
        adapter = ListAdapter(this)

        rvHeroList.adapter = this@FlowListActivity.adapter
    }

    override fun initializeViewModels() {
        flowListVm.heroList.observe(this) { list ->
            list?.let { adapter.submitList(list) }
        }
    }

}