package com.example.mytestapp.screens.update

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel

@Composable
fun ItemUpdateScreen(navController: NavController,
                     itemId: String,
                     itemViewModel: ItemViewModel,
                     dessertViewModel: DessertViewModel,
                     drinksViewModel: DrinksViewModel) {

     itemViewModel.getItem(itemId)
    dessertViewModel.getDessert(itemId)
    drinksViewModel.getDrink(itemId)


   val itemUpdate =  itemViewModel.currentItem
    val dessertUpdate = dessertViewModel.currentDessert
    val drinkUpdate = drinksViewModel.currentDrink



    Column() {
        Text(text = "update item with id :${itemUpdate.value.name}")

    }
    

}