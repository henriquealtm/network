package br.com.henriquealtmayer.network.components.errorcontainer

import androidx.databinding.BindingAdapter

@BindingAdapter("on_try_again")
fun ErrorContainer.setOnTryAgain(
    newValue: (() -> Unit)?
) {
    if (onTryAgain != newValue) {
        onTryAgain = newValue
    }
}