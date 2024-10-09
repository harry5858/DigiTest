package com.test.digiitunes.di

import androidx.room.Room
import com.test.digiitunes.data.local.dao.SavedResultDao
import com.test.digiitunes.data.local.database.SavedResultDatabase
import com.test.digiitunes.data.remote.BASE_URL
import com.test.digiitunes.data.remote.ItunesApi
import com.test.digiitunes.data.repo.ItunesRepositoryImpl
import com.test.digiitunes.domain.repo.ItunesRepository
import com.test.digiitunes.domain.useCases.DeleteSavedEntryUseCase
import com.test.digiitunes.domain.useCases.GetSavedResultUseCase
import com.test.digiitunes.domain.useCases.GetSearchResultUseCase
import com.test.digiitunes.domain.useCases.SaveResultUseCase
import com.test.digiitunes.ui.home.HomeViewModel
import com.test.digiitunes.ui.saved.SavedResultViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<ItunesApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItunesApi::class.java)
    }

    single<SavedResultDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = SavedResultDatabase::class.java,
            name = "SavedResultDatabase"
        ).build()
    }

    single<SavedResultDao> {
        val db = get<SavedResultDatabase>()
        db.searchResultDao()
    }

    single<ItunesRepository> { ItunesRepositoryImpl(get(), get()) }

    single<GetSearchResultUseCase> { GetSearchResultUseCase(get()) }

    single<SaveResultUseCase> { SaveResultUseCase(get()) }

    single<GetSavedResultUseCase> { GetSavedResultUseCase(get()) }

    single<DeleteSavedEntryUseCase> { DeleteSavedEntryUseCase(get()) }

    viewModel { HomeViewModel(get(), get()) }

    viewModel { SavedResultViewModel(get(), get()) }
}