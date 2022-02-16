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
            null -> RestoHomeScreen
            else -> throw IllegalAccessException("Route $route is not recognized")
        }
    }

}