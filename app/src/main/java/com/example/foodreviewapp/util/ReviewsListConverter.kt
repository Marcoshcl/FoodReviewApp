package com.example.foodreviewapp.util

import androidx.room.TypeConverter
import com.example.foodreviewapp.data.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewsListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromReviewList(reviewList: List<Review>): String {
        return gson.toJson(reviewList)
    }

    @TypeConverter
    fun toReviewList(value: String): List<Review> {
        val listType = object : TypeToken<List<Review>>() {}.type
        return gson.fromJson(value, listType)
    }
}