package br.com.henriquealtmayer.network.commons.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.henriquealtmayer.network.commons.extension.smoothScrollToBottom
import br.com.henriquealtmayer.network.commons.handleOptional

@BindingAdapter("on_bottom_refresh")
fun RecyclerView.setOnBottomRefresh(
    onRefresh: (() -> Unit)?
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1)
                && newState == RecyclerView.SCROLL_STATE_IDLE
            ) {
                onRefresh?.invoke()
            }
        }
    })
}

@BindingAdapter("smooth_scroll_to_bottom")
fun RecyclerView.setSmoothScrollToBottom(
    mustScroll: Boolean?
) {
    if (mustScroll.handleOptional()) {
        smoothScrollToBottom()
    }
}