package com.example.mytestapp.navigation

import androidx.browser.browseractions.BrowserActionsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytestapp.model.MItem
import com.example.mytestapp.viewModels.ItemViewModel
import com.example.mytestapp.screens.RestoSplashScreen
import com.example.mytestapp.screens.creatItem.CreatItem
import com.example.mytestapp.screens.details.ItemDetailsScreen
import com.example.mytestapp.screens.home.Home
import com.example.mytestapp.screens.login.RestoLoginScreen
import com.example.mytestapp.screens.search.SearchScreen
import com.example.mytestapp.screens.settings.RestoSettings
import com.example.mytestapp.screens.stats.RestoStatsScreen
import com.example.mytestapp.screens.tables.Tables
import com.example.mytestapp.screens.update.DessertUpdateScreen
import com.example.mytestapp.screens.update.DrinkUpdateScreen
import com.example.mytestapp.screens.update.ItemUpdateScreen
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.TableViewModel

@ExperimentalComposeUiApi
@Composable
fun RestoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RestoScreens.SplashScreen.name) {
        composable(RestoScreens.SplashScreen.name) {
            RestoSplashScreen(navController = navController)
        }

        composable(RestoScreens.RestoHomeScreen.name) {
            val itemViewModel = hiltViewModel<ItemViewModel>()
            val dessertViewModel = hiltViewModel<DessertViewModel>()
            val drinksViewModel = hiltViewModel<DrinksViewModel>()
            val tableViewModel = hiltViewModel<TableViewModel>()
            Home(
                navController = navController,
                itemViewModel = itemViewModel,
                dessertViewModel = dessertViewModel,
                drinksViewModel = drinksViewModel,
                tableViewModel = tableViewModel
            )
        }

        composable(RestoScreens.LoginScreen.name) {
            RestoLoginScreen(navController = navController)
        }

        composable(RestoScreens.DetailScreen.name) {
            val itemViewModel = hiltViewModel<ItemViewModel>()
            val dessertViewModel = hiltViewModel<DessertViewModel>()
            val drinksViewModel = hiltViewModel<DrinksViewModel>()
            ItemDetailsScreen(
                navController = navController,
                itemViewModel = itemViewModel,
                dessertViewModel = dessertViewModel,
                drinksViewModel = drinksViewModel,
            )
        }

        composable(RestoScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(RestoScreens.RestoStatsScreen.name) {
            RestoStatsScreen(navController = navController)
        }



        val updateItem = RestoScreens.UpdateScreen.name
        composable( "$updateItem/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            val itemViewModel = hiltViewModel<ItemViewModel>()
            navBackStackEntry.arguments?.getString("itemId").let {
                ItemUpdateScreen(
                    navController = navController,
                    itemId=  it.toString(),
                    itemViewModel = itemViewModel,
                )
            }

            }



        val updateDessert = RestoScreens.DessertUpdateScreen.name
        composable( "$updateDessert/{dessertId}",
            arguments = listOf(navArgument("dessertId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            val dessertViewModel = hiltViewModel<DessertViewModel>()
            navBackStackEntry.arguments?.getString("dessertId").let {
                DessertUpdateScreen(
                    navController = navController,
                    dessertId=  it.toString(),
                    dessertViewModel= dessertViewModel
                )
            }
        }



        val updateDrink = RestoScreens.DrinksUpdateScreen.name
        composable( "$updateDrink/{drinkId}",
            arguments = listOf(navArgument("drinkId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            val drinksViewModel = hiltViewModel<DrinksViewModel>()
            navBackStackEntry.arguments?.getString("drinkId").let {
                DrinkUpdateScreen(
                    navController = navController,
                    drinkId = it.toString(),
                    drinksViewModel = drinksViewModel
                )
            }
        }



        composable(RestoScreens.RestoSettingsScreen.name) {
            RestoSettings(navController = navController)
        }

        composable(RestoScreens.TablesScreen.name) {
            val tableViewModel = hiltViewModel<TableViewModel>()
            Tables(navController = navController, tableViewModel = tableViewModel)
        }

        composable(RestoScreens.CreatItemScreen.name) {
            val itemViewModel = hiltViewModel<ItemViewModel>()
            val drinksViewModel = hiltViewModel<DrinksViewModel>()
            val dessertViewModel = hiltViewModel<DessertViewModel>()
            CreatItem(
                navController = navController,
                itemViewModel =  itemViewModel,
                drinksViewModel = drinksViewModel,
                dessertViewModel = dessertViewModel
            )
        }
    }
}