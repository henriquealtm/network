package br.com.henriquealtmayer.network.di

import br.com.henriquealtmayer.network.list.flow.data.api.FlowApi
import br.com.henriquealtmayer.network.list.flow.data.repository.FlowRepository
import br.com.henriquealtmayer.network.list.flow.domain.repository.IFlowRepository
import br.com.henriquealtmayer.network.list.flow.domain.usecase.FlowListUseCase
import br.com.henriquealtmayer.network.list.flow.presentation.FlowListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val FLOW_RETROFIT_NAME = "FLOW_RETROFIT"

val flowListModules = module {

    // ViewModel
    viewModel { FlowListViewModel(get()) }

    // UseCase
    factory { FlowListUseCase(flowRepository = get()) }

    // Repository
    single { FlowRepository(get()) } bind IFlowRepository::class

    single(named(FLOW_RETROFIT_NAME)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_NAME)))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>(named(FLOW_RETROFIT_NAME)).create(FlowApi::class.java)
    }

}