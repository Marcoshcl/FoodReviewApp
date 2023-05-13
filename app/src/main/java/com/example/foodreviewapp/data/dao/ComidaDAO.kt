package com.example.foodreviewapp.data.dao

import androidx.room.*
import com.example.foodreviewapp.data.model.Comida

@Dao
interface ComidaDao {
    @Query("SELECT * FROM comida")
    fun getAllRefeições(): List<Comida>

    @Query("SELECT * FROM comida WHERE id = :comidaId")
    fun getComidaById(comidaId: String): Comida?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComida(comida: Comida)

    @Update
    fun updateComida(comida: Comida)

    @Delete
    fun deleteComida(comida: Comida)
}