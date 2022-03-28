package com.example.mytestapp.navigation

import com.example.mytestapp.screens.creatItem.CreatItem

enum class RestoScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    RestoHomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    RestoStatsScreen,
    CreatItemScreen,
    TablesScreen,
    DessertUpdateScreen,
    DrinksUpdateScreen,
    RestoSettingsScreen;
    companion object {
        fun fromRoute(route: String?): RestoScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            RestoHomeScreen.name -> RestoHomeScreen
            SearchScreen.name -> SearchScreen
            DetailScreen.name -> DetailScreen
            UpdateScreen.name -> UpdateScreen
            RestoStatsScreen.name -> RestoStatsScreen
            RestoSettingsScreen.name -> RestoSettingsScreen
            CreatItemScreen.name -> CreatItemScreen
            TablesScreen.name -> TablesScreen
            DessertUpdateScreen.name -> DessertUpdateScreen
            DrinksUpdateScreen.name -> DrinksUpdateScreen
            null -> RestoHomeScreen
            else -> throw IllegalAccessException("Route $route is not recognized")
        }
    }

}