package com.example.mytestapp.screens.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar

@Composable
fun SearchScreen(navController: NavController) {
    Text(text = "seacr baby")
    BottomNavBar(navController)
}