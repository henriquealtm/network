package br.com.henriquealtmayer.network.list.livedata.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.henriquealtmayer.network.R
import br.com.henriquealtmayer.network.databinding.ItemCharacterBinding
import br.com.henriquealtmayer.network.list.livedata.domain.model.Hero


internal class LdListAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Hero, LdListAdapter.ViewHolder>(
    DictListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCharacterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_character, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        currentList[pos].let { heroItem ->
            holder.binding.run {
                item = heroItem
                lifecycleOwner = this@LdListAdapter.lifecycleOwner
            }
        }
    }

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private class DictListDiffCallback : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(
        old: Hero,
        aNew: Hero
    ) = old.id == aNew.id

    override fun areContentsTheSame(
        old: Hero,
        aNew: Hero
    ) = old == aNew

}