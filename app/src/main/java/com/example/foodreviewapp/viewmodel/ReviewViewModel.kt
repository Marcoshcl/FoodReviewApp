package com.example.foodreviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodreviewapp.data.model.Review
import com.example.foodreviewapp.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val reviewRepository: ReviewRepository) : ViewModel() {
    private val _reviewsLiveData = MutableLiveData<List<Review>>()
    val reviewsLiveData: LiveData<List<Review>> = _reviewsLiveData

    fun getReviews() {
        viewModelScope.launch {
            val reviews = reviewRepository.getReviews()
            _reviewsLiveData.value = reviews
        }
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