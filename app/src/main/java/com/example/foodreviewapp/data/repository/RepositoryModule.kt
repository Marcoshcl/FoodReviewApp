package com.example.foodreviewapp.data.repository

import android.content.Context
import androidx.room.Room
import com.example.foodreviewapp.ComidaRepositoryImpl
import com.example.foodreviewapp.data.db.AppDatabase
import com.example.foodreviewapp.viewmodel.ComidaViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "my-database"
    ).build()
}

val appModule = module {
    factory<ComidaRepository> { ComidaRepositoryImpl(get()) }
    single<ComidaRepository> { ComidaRepositoryImpl(get()) }
    single { getDatabase(androidContext()) }
    viewModel { ComidaViewModel(get()) }

}