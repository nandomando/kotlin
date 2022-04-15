package com.example.mytestapp.screens.update

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytestapp.components.itemButton
import com.example.mytestapp.components.itemInputText
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.viewModels.DrinksViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrinkUpdateScreen(navController: NavController,
                      drinkId: String,
                      drinksViewModel: DrinksViewModel) {
    drinksViewModel.getDrink(drinkId)
    val drinkUpdate = drinksViewModel.currentDrink.collectAsState().value
    val context = LocalContext.current


    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Box() {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(modifier = Modifier
                        .then(Modifier.size(37.dp)),
//                        .background(Color.LightGray),
                        onClick = {
                            navController.navigate(RestoScreens.DetailScreen.name)
                        }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="back", tint = Color.Blue)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "${drinkUpdate.name}", color = Color.Blue, fontSize = 23.sp)
                }
            }

        }


        Row(modifier = Modifier.weight(1f)) {
            Column(

                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                if (drinkUpdate.name != null) {
                    var name by remember { mutableStateOf(drinkUpdate.name.toString()) }
                    var description by remember { mutableStateOf(drinkUpdate.description.toString()) }


                    itemInputText(
                        modifier = Modifier.padding(
                            top = 9.dp,
                            bottom = 8.dp
                        ),
                        text = name,
                        label = "Drink",
                        placeholder = "",
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isWhitespace()
                                }) name = it
                        })

                    itemInputText(
                        modifier = Modifier.padding(
                            top = 9.dp,
                            bottom = 8.dp
                        ),
                        text = description,
                        label = "Description",
                        placeholder = "",
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isWhitespace()
                                }) description = it
                        })

                    val price = remember { mutableStateOf(drinkUpdate.price.toString()) }

                    val change: (String) -> Unit = { it ->
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
                        label = { Text(text = "Price") }

                    )

                    Row(modifier = Modifier.fillMaxWidth() .padding(start = 10.dp, end = 10.dp)) {
                        Column(modifier = Modifier.weight(1f) .padding(end = 3.dp)) {
                            Button(onClick = {

//                                drinksViewModel.removeDrink(drinkUpdate)
                                navController.navigate(RestoScreens.DetailScreen.name)
                                Toast.makeText(
                                    context, "Cancel",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                                modifier = Modifier.fillMaxWidth(),) {
                                Text("Cancel", color = Color.White)
                            }
                        }
                        Column(modifier = Modifier.weight(1f) .padding(start = 3.dp)) {
                            itemButton(text = "Save",
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    if (name.isNotEmpty() && description.isNotEmpty() && price.value.isNotEmpty()) {
                                        drinkUpdate.name = name
                                        drinkUpdate.description = description
                                        drinkUpdate.price = price.value
                                        drinksViewModel.updateDrink(drinkUpdate)
//                                name = ""
//                                description = ""
                                        navController.navigate(RestoScreens.DetailScreen.name)
                                        Toast.makeText(
                                            context, "Drink Updated",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }

                    }


                }

            }
        }
    }


}