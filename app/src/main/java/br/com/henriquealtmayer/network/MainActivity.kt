package br.com.henriquealtmayer.network

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.henriquealtmayer.network.databinding.ActivityMainBinding
import br.com.henriquealtmayer.network.list.flow.presentation.FlowListActivity
import br.com.henriquealtmayer.network.list.suspend.presentation.SuspListActivity

class MainActivity : AppCompatActivity() {

    // This Binding is made with View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUi()
    }

    private fun initializeUi() {
        initializeSuspend()
        initializeFlow()
    }

    private fun initializeSuspend() {
        binding.btnSuspend.setOnClickListener {
            startActivity(Intent(this, SuspListActivity::class.java))
        }
    }

    private fun initializeFlow() {
        binding.btnSuspend.setOnClickListener {
            startActivity(Intent(this, FlowListActivity::class.java))
        }
    }

}