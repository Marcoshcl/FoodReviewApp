package com.example.foodreviewapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodreviewapp.util.LocationConverter
import com.example.foodreviewapp.util.StringListConverter
import com.example.foodreviewapplication.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Review::class], version = 6)
@TypeConverters(LocationConverter::class, StringListConverter::class)
abstract class ReviewDatabase : RoomDatabase(){

    abstract fun reviewDao(): ReviewDao

    class Callback @Inject constructor(
        private val database: Provider<ReviewDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().reviewDao()

        }
    }
}