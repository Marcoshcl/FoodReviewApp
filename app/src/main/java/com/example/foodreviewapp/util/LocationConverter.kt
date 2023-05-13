package com.example.foodreviewapp.util

import android.location.Location
import androidx.room.TypeConverter

class LocationConverter {
    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return location?.let { "${it.latitude},${it.longitude}" }
    }

    @TypeConverter
    fun toLocation(value: String?): Location? {
        if (value == null) return null
        val parts = value.split(",")
        return if (parts.size == 2) {
            val latitude = parts[0].toDouble()
            val longitude = parts[1].toDouble()
            val location = Location("")
            location.latitude = latitude
            location.longitude = longitude
            location
        } else {
            null
        }
    }
}