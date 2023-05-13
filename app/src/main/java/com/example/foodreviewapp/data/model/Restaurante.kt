package com.example.foodreviewapp.data.model

import android.location.Location
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurante")
data class Restaurante(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nome: String,
    val localização: Location,
    val reviews: List<Review>
)
