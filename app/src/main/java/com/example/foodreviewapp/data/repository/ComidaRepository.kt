package com.example.foodreviewapp.data.repository

import com.example.foodreviewapp.data.model.Comida

interface ComidaRepository {
    fun getRefeições(): List<Comida>
    fun getComidaById(comidaId: String): Comida?
    fun addComida(comida: Comida)
    fun updateComida(comida: Comida)
    fun deleteComida(comida: Comida)
}