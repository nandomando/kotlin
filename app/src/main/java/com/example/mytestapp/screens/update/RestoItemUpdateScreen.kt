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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytestapp.components.itemButton
import com.example.mytestapp.components.itemInputText
import com.example.mytestapp.model.MItem
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemUpdateScreen(navController: NavController,
                     itemId: String,
                     itemViewModel: ItemViewModel,
                     ) {

    itemViewModel.getItem(itemId)
    val itemUpdate = itemViewModel.currentItem.collectAsState().value
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
                    Text(text = "${itemUpdate.name}", color = Color.Blue, fontSize = 23.sp)
                }
            }

        }


        Row(modifier = Modifier.weight(1f)) {
            Column(

                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                if (itemUpdate.name != null) {
                    var name by remember { mutableStateOf(itemUpdate.name.toString()) }
                    var description by remember { mutableStateOf(itemUpdate.description.toString()) }


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

                    val price = remember { mutableStateOf(itemUpdate.price.toString()) }

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

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp) ) {
                        Column(modifier = Modifier
                            .weight(1f)
                            .padding(end = 3.dp)) {
                            Button(
                                onClick = {
                                        Log.d("", "deletefintion:$itemUpdate")
//                                        itemViewModel.removeItem(itemUpdate)
                                        navController.navigate(RestoScreens.DetailScreen.name)
                                        Toast.makeText(
                                            context, "Cancel",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text("Cancel", color = Color.White)
                            }
                        }

                        Column(modifier = Modifier
                            .weight(1f)
                            .padding(start = 3.dp)) {
                            itemButton(text = "Save",
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    if (name.isNotEmpty() && description.isNotEmpty() && price.value.isNotEmpty()) {
                                        itemUpdate.name = name
                                        itemUpdate.description = description
                                        itemUpdate.price = price.value
                                        itemViewModel.updateItem(itemUpdate)
//                                name = ""
//                                description = ""
                                        navController.navigate(RestoScreens.DetailScreen.name)
                                        Toast.makeText(
                                            context, "Item Updated",
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


//    Column(Modifier.padding(16.dp)) {
//        val textState = remember { mutableStateOf(TextFieldValue()) }
//        TextField(
//            value = textState.value,
//            onValueChange = { textState.value = it }
//        )
//        Text("The textfield has this text: " + textState.value.text)
//    }




//    Scaffold(topBar = {
//        ReaderAppBar(title = "Update Book",
//            icon = Icons.Default.ArrowBack,
//            showProfile = false,
//            navController = navController){
//            navController.popBackStack()
//        }
//    }) {}
