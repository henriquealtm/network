package br.com.henriquealtmayer.network.components.errorcontainer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import br.com.henriquealtmayer.network.R
import kotlinx.android.synthetic.main.error_container.view.*

open class ErrorContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    var onTryAgain: (() -> Unit)? = null
        set(value) {
            field = value
            btn_try_again.setOnClickListener { onTryAgain?.invoke() }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.error_container, this, true)
    }

}