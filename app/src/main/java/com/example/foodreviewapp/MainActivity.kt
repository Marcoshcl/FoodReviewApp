package com.example.foodreviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodreviewapp.ui.screens.RestauranteListScreen
import com.example.foodreviewapp.ui.screens.ReviewListScreen
import com.example.foodreviewapp.util.Screen
import com.example.foodreviewapp.viewmodel.RestauranteViewModel
import com.example.foodreviewapp.viewmodel.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var restauranteViewModel: RestauranteViewModel
    private val reviewViewModel: ReviewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.RESTAURANTE_LIST.name) {
                composable(Screen.RESTAURANTE_LIST.name) {
                    RestauranteListScreen(restauranteViewModel,navController)
                }
                composable(Screen.REVIEW_LIST.name) {
                    ReviewListScreen(reviewViewModel,navController)
                }
            }
        }
    }
}