package com.example.foodreviewapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "restaurante_id") val restauranteId: Int,
    val nota: Float,
    val comentario: String,
    val data: Long
)