package com.example.mytestapp.screens.creatItem

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytestapp.components.itemButton
import com.example.mytestapp.components.itemInputText
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreatItem(navController: NavController,
              itemViewModel: ItemViewModel,
              dessertViewModel: DessertViewModel,
              drinksViewModel: DrinksViewModel)
    {

        var name by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        val context = LocalContext.current

        val checkedPlats = remember { mutableStateOf(true)}
        val checkedDessert = remember { mutableStateOf(false)}
        val checkedDrinks = remember { mutableStateOf(false)}



        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(modifier = Modifier
                        .then(Modifier.size(37.dp)),
//                        .background(Color.LightGray),
                        onClick = {
                        navController.navigate(RestoScreens.RestoSettingsScreen.name)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="back", tint = Color.Blue)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        onClick = {
                            checkedPlats.value = true
                            checkedDrinks.value = false
                            checkedDessert.value = false
                        }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if (checkedPlats.value) Color.LightGray else Color.Transparent
                        )
                    ) {
                        Text(text = "Plats", color = Color.Blue)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        onClick = {
                            checkedPlats.value = false
                            checkedDrinks.value = false
                            checkedDessert.value = true
                        }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if (checkedDessert.value) Color.LightGray else Color.Transparent
                        )
                    ) {
                        Text(text = "Dessert", color = Color.Blue)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        onClick = {
                            checkedPlats.value = false
                            checkedDrinks.value = true
                            checkedDessert.value = false
                        }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if (checkedDrinks.value) Color.LightGray else Color.Transparent
                        )
                    ) {
                        Text(text = "Drinks", color = Color.Blue)
                    }

                }
            }

            Divider(modifier = Modifier.padding(1.dp))


            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                itemInputText(
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp),
                    text = name,
                    label = "Product",
                    onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) name = it
                    } )

                itemInputText(
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp),
                    text = description,
                    label = "Description",
                    onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) description = it
                    })

                var price = remember { mutableStateOf("")}

                val change : (String) -> Unit = { it ->
                    price.value = it  // it is supposed to be this
                }

                TextField(
                    value = price.value,
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = change,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    label = { Text(text = "Price")}

                )

                itemButton(text = "Save",
                    onClick = {
                        if (name.isNotEmpty() && description.isNotEmpty() && price.value.isNotEmpty()) {
                            if(checkedPlats.value) {
                                itemViewModel.addItem(MItem(name = name,
                                    description = description,
                                    price = price.value))
                                        name = ""
                                description = ""
                                navController.navigate(RestoScreens.RestoHomeScreen.name)
                                        Toast.makeText(context, "Item Added",
                                Toast.LENGTH_SHORT).show()
                            }
                            if(checkedDessert.value) {
                                dessertViewModel.addDessert(
                                    MItemDessert(name = name,
                                    description = description,
                                    price = price.value)
                                )
                                name = ""
                                description = ""
                                navController.navigate(RestoScreens.RestoHomeScreen.name)
                                Toast.makeText(context, "Dessert Added",
                                    Toast.LENGTH_SHORT).show()
                            }
                            if (checkedDrinks.value) {
                                drinksViewModel.addDrink(
                                    MItemDrinks(name = name,
                                    description = description,
                                    price = price.value)
                                )
                                name = ""
                                description = ""
                                navController.navigate(RestoScreens.RestoHomeScreen.name)
                                Toast.makeText(context, "Drink Added",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
            }

        }
}