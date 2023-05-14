package com.example.foodreviewapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodreviewapp.data.model.Restaurante
import com.example.foodreviewapp.viewmodel.RestauranteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RestauranteListScreen(navController: NavController) {
    val viewModel: RestauranteViewModel = getViewModel()
    val restaurantes = remember { mutableStateListOf<Restaurante>() }

    LaunchedEffect(Unit) {
        val listaRestaurantes = viewModel.getRestaurantes()
        restaurantes.addAll( listaRestaurantes)
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(restaurantes) { restaurante ->
            RestauranteListItem(restaurante)
        }
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
        Text(text = restaurante.localizacao.toString())
    }
}
