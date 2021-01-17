package br.com.henriquealtmayer.network

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriquealtmayer.network.list.flow.presentation.FlowListActivity
import br.com.henriquealtmayer.network.list.suspend.presentation.SuspListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUi()
    }

    private fun initializeUi() {
        initializeSuspend()
        initializeFlow()
    }

    private fun initializeSuspend() {
        btn_suspend.setOnClickListener {
            startActivity(Intent(this, SuspListActivity::class.java))
        }
    }

    private fun initializeFlow() {
        btn_flow.setOnClickListener {
            startActivity(Intent(this, FlowListActivity::class.java))
        }
    }

}