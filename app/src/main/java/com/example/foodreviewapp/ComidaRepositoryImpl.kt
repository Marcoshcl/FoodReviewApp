package com.example.foodreviewapp

import com.example.foodreviewapp.data.dao.ComidaDao
import com.example.foodreviewapp.data.model.Comida
import com.example.foodreviewapp.data.repository.ComidaRepository

class ComidaRepositoryImpl(private val comidaDao: ComidaDao) : ComidaRepository {

    override fun getRefeições(): List<Comida> {
        return comidaDao.getAllRefeições()
    }

    override fun getComidaById(comidaId: String): Comida? {
        return comidaDao.getComidaById(comidaId)
    }

    override fun addComida(comida: Comida) {
        return comidaDao.insertComida(comida)
    }

    override fun updateComida(comida: Comida) {
        return comidaDao.updateComida(comida)
    }

    override fun deleteComida(comida: Comida) {
        return comidaDao.deleteComida(comida)
    }
}