package com.example.foodreviewapp.data.repository

import com.example.foodreviewapp.data.model.Restaurante

interface RestauranteRepository {
    fun getRestaurantes(): List<Restaurante>
    fun getRestauranteById(restauranteId: String): Restaurante?
    fun addRestaurante(restaurante: Restaurante)
    fun updateRestaurante(restaurante: Restaurante)
    fun deleteRestaurante(restaurante: Restaurante)
}