package com.example.foodreviewapp

import com.example.foodreviewapp.data.dao.ReviewDao
import com.example.foodreviewapp.data.model.Review
import com.example.foodreviewapp.data.repository.ReviewRepository

class ReviewRepositoryImpl(private val reviewDao: ReviewDao) : ReviewRepository {
    override fun getReviews(): List<Review> {
        return reviewDao.getAllReviews()
    }

    override fun getReviewsByComidaId(comidaId: String): List<Review> {
        return reviewDao.getReviewsByComidaId(comidaId)
    }

    override fun addReview(review: Review) {
        return reviewDao.insertReview(review)
    }

    override fun updateReview(review: Review) {
        return reviewDao.updateReview(review)
    }

    override fun deleteReview(review: Review) {
        return reviewDao.deleteReview(review)
    }
}