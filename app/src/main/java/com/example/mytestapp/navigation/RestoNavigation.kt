package com.example.mytestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.screens.RestoSplashScreen
import com.example.mytestapp.screens.details.ItemDetailsScreen
import com.example.mytestapp.screens.home.Home
import com.example.mytestapp.screens.login.RestoLoginScreen
import com.example.mytestapp.screens.search.SearchScreen
import com.example.mytestapp.screens.settings.RestoSettings
import com.example.mytestapp.screens.stats.RestoStatsScreen
import com.example.mytestapp.screens.update.ItemUpdateScreen

@ExperimentalComposeUiApi
@Composable
fun RestoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RestoScreens.SplashScreen.name) {
        composable(RestoScreens.SplashScreen.name) {
            RestoSplashScreen(navController = navController)
        }

        composable(RestoScreens.RestoHomeScreen.name) {
            Home(navController = navController)
        }

        composable(RestoScreens.LoginScreen.name) {
            RestoLoginScreen(navController = navController)
        }

        composable(RestoScreens.DetailScreen.name) {
            ItemDetailsScreen(navController = navController)
        }

        composable(RestoScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(RestoScreens.RestoStatsScreen.name) {
            RestoStatsScreen(navController = navController)
        }

        composable(RestoScreens.UpdateScreen.name) {
            ItemUpdateScreen(navController = navController)
        }

        composable(RestoScreens.RestoSettingsScreen.name) {
            RestoSettings(navController = navController)
        }
    }
}