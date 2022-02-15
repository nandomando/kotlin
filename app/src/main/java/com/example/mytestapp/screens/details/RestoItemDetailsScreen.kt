package com.example.mytestapp.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar

@Composable
fun ItemDetailsScreen(navController: NavController) {
    Text(text = "details")
    BottomNavBar(navController)
}