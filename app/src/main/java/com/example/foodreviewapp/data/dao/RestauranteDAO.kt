package com.example.foodreviewapp.data.dao

import androidx.room.*
import com.example.foodreviewapp.data.model.Restaurante

@Dao
interface RestauranteDao {
    @Query("SELECT * FROM restaurante")
    fun getAllRestaurantes(): List<Restaurante>

    @Query("SELECT * FROM restaurante WHERE id = :restauranteId")
    fun getRestauranteById(restauranteId: String): Restaurante?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurante(restaurante: Restaurante)

    @Update
    fun updateRestaurante(restaurante: Restaurante)

    @Delete
    fun deleteRestaurante(restaurante: Restaurante)
}