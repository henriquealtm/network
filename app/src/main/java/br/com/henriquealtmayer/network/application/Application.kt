package br.com.henriquealtmayer.network.application

import android.app.Application
import br.com.henriquealtmayer.network.di.commonsModule
import br.com.henriquealtmayer.network.di.flowListModules
import br.com.henriquealtmayer.network.di.liveDataListModules
import br.com.henriquealtmayer.network.di.suspendListModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@Application)
            modules(
                commonsModule,
                liveDataListModules,
                suspendListModules,
                flowListModules
            )
        }
    }

}