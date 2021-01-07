package br.com.henriquealtmayer.network.di

import br.com.henriquealtmayer.network.list.livedata.data.repository.LdRepository
import br.com.henriquealtmayer.network.list.livedata.domain.repository.ILdRepository
import br.com.henriquealtmayer.network.list.livedata.domain.usecase.LdListUseCase
import br.com.henriquealtmayer.network.list.livedata.presentation.LdListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val liveDataListModules = module {
    // ViewModel
    viewModel { LdListViewModel(listUseCase = get()) }

    // UseCase
    factory { LdListUseCase(ldRepository = get()) }

    // Repository
    single { LdRepository(get()) } bind ILdRepository::class
}