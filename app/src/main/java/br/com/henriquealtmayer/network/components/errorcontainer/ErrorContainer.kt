package br.com.henriquealtmayer.network.components.errorcontainer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import br.com.henriquealtmayer.network.databinding.ErrorContainerBinding

open class ErrorContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ErrorContainerBinding =
        ErrorContainerBinding.inflate(LayoutInflater.from(context), this, true)

    var onTryAgain: (() -> Unit)? = null
        set(value) {
            field = value
            binding.btnTryAgain.setOnClickListener { onTryAgain?.invoke() }
        }

}