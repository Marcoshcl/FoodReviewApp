package com.example.foodreviewapp.data.repository

import android.app.Application
import androidx.room.Room
import com.example.foodreviewapp.ComidaRepositoryImpl
import com.example.foodreviewapp.RestauranteRepositoryImpl
import com.example.foodreviewapp.ReviewRepositoryImpl

import com.example.foodreviewapp.data.dao.ComidaDao
import com.example.foodreviewapp.data.dao.RestauranteDao
import com.example.foodreviewapp.data.dao.ReviewDao
import com.example.foodreviewapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideComidaRepository(comidaDao: ComidaDao): ComidaRepository {
        return ComidaRepositoryImpl(comidaDao) // Substitua ComidaRepositoryImpl() pela implementação real do repositório
    }

    @Provides
    @Singleton
    fun provideRestauranteRepository(restauranteDao: RestauranteDao): RestauranteRepository {
        return RestauranteRepositoryImpl(restauranteDao) // Substitua ComidaRepositoryImpl() pela implementação real do repositório
    }

    @Provides
    @Singleton
    fun provideReviewRepository(reviewDao: ReviewDao): ReviewRepository {
        return ReviewRepositoryImpl(reviewDao) // Substitua ComidaRepositoryImpl() pela implementação real do repositório
    }

    @Provides
    @Singleton
    fun provideComidaDao(application: Application): ComidaDao {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app-database")
            .build()
            .comidaDao()
    }

    @Provides
    @Singleton
    fun provideRestauranteDao(application: Application): RestauranteDao {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app-database")
            .build()
            .restauranteDao()
    }

    @Provides
    @Singleton
    fun provideReviewDao(application: Application): ReviewDao {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app-database")
            .build()
            .reviewDao()
    }
}