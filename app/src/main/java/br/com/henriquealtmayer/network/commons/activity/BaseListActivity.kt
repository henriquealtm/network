package br.com.henriquealtmayer.network.commons.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.henriquealtmayer.network.R
import br.com.henriquealtmayer.network.commons.listadapter.ListAdapter
import br.com.henriquealtmayer.network.databinding.ActivityBaseListBinding
import kotlinx.android.synthetic.main.activity_base_list.*

abstract class BaseListActivity : AppCompatActivity() {

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (DataBindingUtil.setContentView(
            this,
            R.layout.activity_base_list
        ) as? ActivityBaseListBinding)?.apply {
            lifecycleOwner = this@BaseListActivity
            loadVm(this)
        }

        initializeUI()
        initializeViewModels()
    }

    private fun loadVm(binding: ActivityBaseListBinding) {
        binding.vm = listViewModel
    }

    private fun initializeUI() {
        createListAdapter()
    }

    private fun createListAdapter() {
        adapter = ListAdapter(this)
        rvHeroList.adapter = this@BaseListActivity.adapter
    }

    private fun initializeViewModels() {
        listViewModel.heroList.observe(this) { list ->
            list?.let { adapter.submitList(list) }
        }
    }

    protected abstract val listViewModel: BaseListViewModel

}