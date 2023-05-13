package com.example.foodreviewapp.data.dao

import androidx.room.*
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.data.model.Review

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review")
    fun getAllReviews(): List<Review>

    @Query("SELECT * FROM review WHERE comidaId = :comidaId")
    fun getReviewsByComidaId(comidaId: String): List<Review>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReview(review: Review)

    @Update
    fun updateReview(review: Review)

    @Delete
    fun deleteReview(review: Review)
}