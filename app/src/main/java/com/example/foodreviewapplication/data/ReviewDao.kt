package com.example.foodreviewapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    fun getReviews(query: String, sortOrder: SortOrder) : Flow<List<Review>> =
        when(sortOrder) {
            SortOrder.BY_DATE -> getReviewsSortedByDateCreated(query)
            SortOrder.BY_NAME -> getReviewsSortedByDateCreated(query)
        }

    @Query("SELECT * FROM review_table WHERE nomeRestaurante LIKE '%' || :searchQuery || '%'")
    fun getReviewsSortedByName(searchQuery: String): Flow<List<Review>>

    @Query("SELECT * FROM review_table WHERE nomeRestaurante LIKE '%' || :searchQuery || '%'")
    fun getReviewsSortedByDateCreated(searchQuery: String): Flow<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review)

    @Update
    suspend fun update(review: Review)

    @Delete
    suspend fun delete(review: Review)
}