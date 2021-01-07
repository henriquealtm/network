package br.com.henriquealtmayer.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.henriquealtmayer.network.list.livedata.presentation.LdListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUi()
    }

    private fun initializeUi() {
        btn_live_data.setOnClickListener {
            startActivity(Intent(this, LdListActivity::class.java))
        }
    }

}