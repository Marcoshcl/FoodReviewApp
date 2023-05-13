package com.example.foodreviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.data.repository.RestauranteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestauranteViewModel @Inject constructor(private val restauranteRepository: RestauranteRepository) : ViewModel() {
    private val _restaurantesLiveData = MutableLiveData<List<Restaurante>>()

    val restaurantesLiveData: LiveData<List<Restaurante>> = _restaurantesLiveData

    fun getRestaurantes() {
        viewModelScope.launch {
            val restaurantes = restauranteRepository.getRestaurantes()
            _restaurantesLiveData.value = restaurantes
        }
    }

    fun addRestaurante(restaurante: Restaurante) {
        viewModelScope.launch {
            restauranteRepository.addRestaurante(restaurante)
        }
    }

    fun updateRestaurante(restaurante: Restaurante) {
        viewModelScope.launch {
            restauranteRepository.updateRestaurante(restaurante)
        }
    }

    fun deleteRestaurante(restaurante: Restaurante) {
        viewModelScope.launch {
            restauranteRepository.deleteRestaurante(restaurante)
        }
    }
}