package com.example.mytestapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel

@Composable
fun ItemDetailsScreen(navController: NavController,
                      itemViewModel: ItemViewModel,
                      dessertViewModel: DessertViewModel,
                      drinksViewModel: DrinksViewModel ) {

    val drinksList = drinksViewModel.drinksList.collectAsState().value
    val dessertList = dessertViewModel.dessertList.collectAsState().value
    val itemsList = itemViewModel.itemList.collectAsState().value
    val scrollState = rememberScrollState()



    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
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
            Column() {
                Text(text = "Menu")
            }
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState)
            ) {

                if (itemsList.isNotEmpty()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier, horizontalAlignment = Alignment.Start ) {
                            Text(text = "Plates", fontWeight = FontWeight.Bold)
                        }
                    }
                    Divider(modifier = Modifier,
                        color = Color.Black,
                        thickness = 1.dp,
                        startIndent = 22.dp)
                }

                itemsList.forEach { item  ->
                    Card(modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            navController.navigate(RestoScreens.UpdateScreen.name + "/${item.plateId}")
                        }
                    ) {
                        Column() {

                        }
                        Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
                            Row(modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                                    Text(text = "${item.name}")
                                }
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                    Text(text = "$ ${item.price}")
                                }
                            }
                            Row() {
                                Text(text = "${item.description}")
                            }

                        }
                        
                    }
                }

                if (dessertList.isNotEmpty()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier, horizontalAlignment = Alignment.Start ) {
                            Text(text = "Desserts", fontWeight = FontWeight.Bold)
                        }
                    }
                    Divider(modifier = Modifier,
                        color = Color.Black,
                        thickness = 1.dp,
                        startIndent = 22.dp)
                }

                dessertList.forEach { item ->
                    Card(modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            navController.navigate(RestoScreens.UpdateScreen.name + "/${item.dessertId}")

                        }
                    ) {
                        Column() {

                        }
                        Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
                            Row(modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                                    Text(text = "${item.name}")
                                }
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                    Text(text = "$ ${item.price}")
                                }
                            }
                            Row() {
                                Text(text = "${item.description}")
                            }

                        }
                    }
                }

                if (drinksList.isNotEmpty()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier, horizontalAlignment = Alignment.Start ) {
                            Text(text = "Drinks", fontWeight = FontWeight.Bold)
                        }
                    }
                    Divider(modifier = Modifier,
                        color = Color.Black,
                        thickness = 1.dp,
                        startIndent = 22.dp)
                }

                drinksList.forEach { item ->
                    Card(modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                            navController.navigate(RestoScreens.UpdateScreen.name + "/${item.drinksId}")
                        }
                    ) {
                        Column() {

                        }
                        Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
                            Row(modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                                    Text(text = "${item.name}")
                                }
                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                    Text(text = "$ ${item.price}")
                                }
                            }
                            Row() {
                                Text(text = "${item.description}")
                            }

                        }
                    }
                }



            }

        }
        
    }
}