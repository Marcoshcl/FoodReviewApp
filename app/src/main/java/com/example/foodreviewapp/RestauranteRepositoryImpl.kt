package com.example.foodreviewapp

import com.example.foodreviewapp.data.dao.RestauranteDao
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.data.repository.RestauranteRepository

class RestauranteRepositoryImpl(private val restauranteDao: RestauranteDao) : RestauranteRepository {
    override fun getRestaurantes(): List<Restaurante> {
        return restauranteDao.getAllRestaurantes()
    }

    override fun getRestauranteById(restauranteId: String): Restaurante? {
        return restauranteDao.getRestauranteById(restauranteId)
    }

    override fun addRestaurante(restaurante: Restaurante) {
        return restauranteDao.insertRestaurante(restaurante)
    }

    override fun updateRestaurante(restaurante: Restaurante) {
        return restauranteDao.updateRestaurante(restaurante)
    }

    override fun deleteRestaurante(restaurante: Restaurante) {
        return restauranteDao.deleteRestaurante(restaurante)
    }
}