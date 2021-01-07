package br.com.henriquealtmayer.network.list.livedata.presentation

import br.com.henriquealtmayer.network.R
import br.com.henriquealtmayer.network.commons.activity.BaseActivity
import br.com.henriquealtmayer.network.databinding.ActivityLdListBinding
import br.com.henriquealtmayer.network.list.livedata.presentation.adapter.LdListAdapter
import kotlinx.android.synthetic.main.activity_ld_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class LdListActivity : BaseActivity<ActivityLdListBinding>(
    R.layout.activity_ld_list
) {

    private val ldListVm: LdListViewModel by viewModel()

    override val loadVm: (ActivityLdListBinding) -> Unit = { binding ->
        binding.vm = ldListVm
    }

    private lateinit var adapter: LdListAdapter

    override fun initializeUI() {
        super.initializeUI()

        createListAdapter()
    }

    private fun createListAdapter() {
        adapter = LdListAdapter(this)

        rvHeroList.adapter = this@LdListActivity.adapter
    }

    override fun initializeViewModels() {
        ldListVm.heroList.observe(this) { list ->
            list?.let { adapter.submitList(list) }
        }
    }

}