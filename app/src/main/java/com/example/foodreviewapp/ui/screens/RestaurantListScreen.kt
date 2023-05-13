package com.example.foodreviewapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.util.Screen
import com.example.foodreviewapp.viewmodel.RestauranteViewModel

@Composable
fun RestauranteListScreen(
    viewModel: RestauranteViewModel,
    navController: NavController
) {
    val restaurantes by viewModel.restaurantesLiveData.observeAsState(initial = emptyList())

    LazyColumn {
        itemsIndexed(restaurantes) { index, restaurante ->
            RestauranteListItem(restaurante = restaurante)
        }
    }

    Button(onClick = { navController.navigate(Screen.REVIEW_LIST.name) }) {
        Text(text = "Ver Avaliações")
    }
}

@Composable
fun RestauranteListItem(restaurante: Restaurante) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Lógica de manipulação de clique no restaurante */ }
            .padding(16.dp)
    ) {
        Text(text = restaurante.nome)
        Text(text = restaurante.localização.toString())
    }
}