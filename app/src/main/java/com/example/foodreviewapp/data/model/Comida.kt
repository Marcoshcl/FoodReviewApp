package com.example.foodreviewapp.data.model

import android.location.Location
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comida")
data class Comida(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val descrição: String,
    val nota: Float,
    val comentario: String,
    val restauranteId: String?,
    val data: Long,
    val localizacao: Location?,
    val fotos: List<String>
)
