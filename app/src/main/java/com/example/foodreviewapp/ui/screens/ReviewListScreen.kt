package com.example.foodreviewapp.ui.screens

import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodreviewapp.data.model.Review
import com.example.foodreviewapp.util.Screen
import com.example.foodreviewapp.viewmodel.ReviewViewModel

@Composable
fun ReviewListScreen(
    viewModel: ReviewViewModel,
    navController: NavController
) {
    val reviews by viewModel.reviewsLiveData.observeAsState(initial = emptyList())

    LazyColumn {
        itemsIndexed(reviews) { index, review ->
            ReviewListItem(review = review)
        }
    }

    Button(onClick = { navController.navigate(Screen.RESTAURANTE_LIST.name) }) {
        Text(text = "Voltar para Restaurantes")
    }
}

@Composable
fun ReviewListItem(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Lógica de manipulação de clique na avaliação */ }
            .padding(16.dp)
    ) {
        Text(text = review.comentario)
        RatingBar(rating = review.nota)
    }
}

@Composable
fun RatingBar(rating: Float, maxRating: Int = 5) {
    val fullStarIcon = Icons.Filled.Star
    val emptyStarIcon = Icons.Outlined.Star

    Row {
        repeat(maxRating) { index ->
            Icon(
                imageVector = if (index < rating) fullStarIcon else emptyStarIcon,
                contentDescription = null
            )
        }
    }
}