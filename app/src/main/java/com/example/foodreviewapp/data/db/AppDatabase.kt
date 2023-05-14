package com.example.foodreviewapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodreviewapp.data.dao.ComidaDao
import com.example.foodreviewapp.data.dao.RestauranteDao
import com.example.foodreviewapp.data.dao.ReviewDao
import com.example.foodreviewapp.data.model.Comida
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.data.model.Review
import com.example.foodreviewapp.util.LocationConverter
import com.example.foodreviewapp.util.StringListConverter

// Classe de banco de dados principal
@Database(entities = [Comida::class, Restaurante::class, Review::class], version = 1)
@TypeConverters(LocationConverter::class,StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comidaDao(): ComidaDao
    abstract fun restauranteDao(): RestauranteDao
    abstract fun reviewDao(): ReviewDao

    companion object {
        private const val DATABASE_NAME = "comida_review_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}