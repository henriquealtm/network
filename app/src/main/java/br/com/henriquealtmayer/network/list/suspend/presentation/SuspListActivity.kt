package br.com.henriquealtmayer.network.list.suspend.presentation

import br.com.henriquealtmayer.network.R
import br.com.henriquealtmayer.network.commons.activity.BaseActivity
import br.com.henriquealtmayer.network.databinding.ActivitySuspListBinding
import br.com.henriquealtmayer.network.commons.listadapter.ListAdapter
import kotlinx.android.synthetic.main.activity_ld_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class SuspListActivity : BaseActivity<ActivitySuspListBinding>(
    R.layout.activity_susp_list
) {

    private val ldListVm: SuspListViewModel by viewModel()

    override val loadVm: (ActivitySuspListBinding) -> Unit = { binding ->
        binding.vm = ldListVm
    }

    private lateinit var adapter: ListAdapter

    override fun initializeUI() {
        super.initializeUI()

        createListAdapter()
    }

    private fun createListAdapter() {
        adapter = ListAdapter(this)

        rvHeroList.adapter = this@SuspListActivity.adapter
    }

    override fun initializeViewModels() {
        ldListVm.heroList.observe(this) { list ->
            list?.let { adapter.submitList(list) }
        }
    }

}