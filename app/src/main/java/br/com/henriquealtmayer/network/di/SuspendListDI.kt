package br.com.henriquealtmayer.network.di

import br.com.henriquealtmayer.network.list.suspend.data.api.SuspApi
import br.com.henriquealtmayer.network.list.suspend.data.repository.SuspRepository
import br.com.henriquealtmayer.network.list.suspend.domain.repository.ISuspRepository
import br.com.henriquealtmayer.network.list.suspend.domain.usecase.SuspListUseCase
import br.com.henriquealtmayer.network.list.suspend.presentation.SuspListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SUSP_RETROFIT_NAME = "SUSP_RETROFIT"

val suspendListModules = module {
    // ViewModel
    viewModel { SuspListViewModel(listUseCase = get()) }

    // UseCase
    factory { SuspListUseCase(suspRepository = get()) }

    // Repository
    single { SuspRepository(get()) } bind ISuspRepository::class

    single(named(SUSP_RETROFIT_NAME)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_NAME)))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>(named(SUSP_RETROFIT_NAME)).create(SuspApi::class.java)
    }
}