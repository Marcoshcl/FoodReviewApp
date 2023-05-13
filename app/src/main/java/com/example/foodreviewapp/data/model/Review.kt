package com.example.foodreviewapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val comidaId: String,
    val nota: Float,
    val comentario: String,
    val data: Long
)