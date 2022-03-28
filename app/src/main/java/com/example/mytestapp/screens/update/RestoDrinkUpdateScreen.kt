package com.example.mytestapp.screens.update

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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

        Row() {
            Column() {
                Text(text = "update item with id :${drinkUpdate.name}")
                Text(text = "update item with id :${drinkUpdate.description}")

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


                    Log.d("", "MUTABLEinsideTEXTFIELD:$name ")
                    itemInputText(
                        modifier = Modifier.padding(
                            top = 9.dp,
                            bottom = 8.dp
                        ),
                        text = name,
                        label = "Plate",
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
                        text = description.toString(),
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

                    itemButton(text = "Save",
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