package com.example.foodreviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodreviewapp.data.model.Review
import com.example.foodreviewapp.data.repository.ReviewRepository
import kotlinx.coroutines.launch

class ReviewViewModel (private val reviewRepository: ReviewRepository) : ViewModel() {
    private val _reviewsLiveData = MutableLiveData<List<Review>>()
    val reviewsLiveData: LiveData<List<Review>> = _reviewsLiveData

    fun getReviews() : List<Review> {
        viewModelScope.launch {
            val reviews = reviewRepository.getReviews()
            _reviewsLiveData.value = reviews
        }
        return reviewRepository.getReviews()
    }

    fun addReview(review: Review) {
        viewModelScope.launch {
            reviewRepository.addReview(review)
        }
    }

    fun updateReview(review: Review) {
        viewModelScope.launch {
            reviewRepository.updateReview(review)
        }
    }

    fun deleteReview(review: Review) {
        viewModelScope.launch {
            reviewRepository.deleteReview(review)
        }
    }
}