package com.example.foodreviewapp.data.repository

import com.example.foodreviewapp.data.model.Review

interface ReviewRepository {
    fun getReviews(): List<Review>
    fun getReviewsByComidaId(comidaId: String): List<Review>
    fun addReview(review: Review)
    fun updateReview(review: Review)
    fun deleteReview(review: Review)
}