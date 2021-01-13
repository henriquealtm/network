package br.com.henriquealtmayer.network.di

//import br.com.henriquealtmayer.network.list.livedata.data.api.LdApi
//import br.com.henriquealtmayer.network.list.livedata.data.repository.LdRepository
//import br.com.henriquealtmayer.network.list.livedata.domain.repository.ILdRepository
//import br.com.henriquealtmayer.network.list.livedata.domain.usecase.LdListUseCase
//import br.com.henriquealtmayer.network.list.livedata.presentation.LdListViewModel
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import org.koin.android.viewmodel.dsl.viewModel
//import org.koin.core.qualifier.named
//import org.koin.dsl.bind
//import org.koin.dsl.module
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//private const val LD_RETROFIT_NAME = "LD_RETROFIT"
//
//val liveDataListModules = module {
//    // ViewModel
//    viewModel { LdListViewModel(listUseCase = get()) }
//
//    // UseCase
//    factory { LdListUseCase(ldRepository = get()) }
//
//    // Repository
//    single { LdRepository(get()) } bind ILdRepository::class
//
//    single(named(LD_RETROFIT_NAME)) {
//        Retrofit.Builder()
//            .baseUrl(get<String>(named(BASE_URL_NAME)))
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(get())
//            .build()
//    }
//
//    single {
//        get<Retrofit>(named(LD_RETROFIT_NAME)).create(LdApi::class.java)
//    }
//}