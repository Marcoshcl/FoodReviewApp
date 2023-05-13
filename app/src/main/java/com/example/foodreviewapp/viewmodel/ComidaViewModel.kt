package com.example.foodreviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodreviewapp.data.model.Comida
import com.example.foodreviewapp.data.repository.ComidaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComidaViewModel @Inject constructor(private val comidaRepository: ComidaRepository) : ViewModel() {
    private val _mealsLiveData = MutableLiveData<List<Comida>>()
    val mealsLiveData: LiveData<List<Comida>> = _mealsLiveData

    fun getAllComidas() {
        viewModelScope.launch {
            val meals = comidaRepository.getRefeições()
            _mealsLiveData.value = meals
        }
    }

    fun addComida(comida: Comida) {
        viewModelScope.launch {
            comidaRepository.addComida(comida)
        }
    }

    fun updateComida(comida: Comida) {
        viewModelScope.launch {
            comidaRepository.updateComida(comida)
        }
    }

    fun deleteComida(comida: Comida) {
        viewModelScope.launch {
            comidaRepository.deleteComida(comida)
        }
    }
}