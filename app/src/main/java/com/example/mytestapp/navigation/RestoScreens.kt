package com.example.mytestapp.navigation

enum class RestoScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    RestoHomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    RestoStatsScreen;
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
            null -> RestoHomeScreen
            else -> throw IllegalAccessException("Route $route is not recognized")
        }
    }

}