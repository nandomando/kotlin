package com.example.mytestapp.screens.details

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytestapp.components.DeleteViewDessert
import com.example.mytestapp.components.DeleteViewDrink
import com.example.mytestapp.components.DeleteViewPlate
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ItemDetailsScreen(navController: NavController,
                      itemViewModel: ItemViewModel,
                      dessertViewModel: DessertViewModel,
                      drinksViewModel: DrinksViewModel ) {

    val drinksList = drinksViewModel.drinksList.collectAsState().value
    val dessertList = dessertViewModel.dessertList.collectAsState().value
    val itemsList = itemViewModel.itemList.collectAsState().value


//    val dessertList = dessertViewModel.dessertListMutable
//    val drinksList = drinksViewModel.drinkListMutable
//    val itemsList = itemViewModel.platsList


    val scrollState = rememberScrollState()




    var currentDessert by remember { mutableStateOf(MItemDessert())}
    var currentDrink by remember { mutableStateOf(MItemDrinks())}
    var currentItem by remember { mutableStateOf(MItem())}


    val inputDialogState = remember { mutableStateOf(false) }
    val inputDialogStateDessert = remember { mutableStateOf(false) }
    val inputDialogStateDrinks = remember { mutableStateOf(false) }






    if (inputDialogState.value) {
        AlertDialog(
            onDismissRequest = {
                inputDialogState.value = false
            },
            text = { DeleteViewPlate(currentItem) },

            dismissButton = {
                Button(onClick = { inputDialogState.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    itemViewModel.removeItem(currentItem)
                    inputDialogState.value = false }) {
                    Text("Delete")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }

    if (inputDialogStateDessert.value) {
        AlertDialog(
            onDismissRequest = {
                inputDialogStateDessert.value = false
            },
            text = { DeleteViewDessert(currentDessert) },

            dismissButton = {
                Button(onClick = { inputDialogStateDessert.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {

                    dessertViewModel.removeDessert(currentDessert)

                    inputDialogStateDessert.value = false }) {
                    Text("Delete")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }
    //////////////////////////////////POP UP DELETE DRINKS////////////

    if (inputDialogStateDrinks.value) {
        AlertDialog(
            onDismissRequest = {
                inputDialogStateDrinks.value = false
            },
            text = { DeleteViewDrink(currentDrink) },

            dismissButton = {
                Button(onClick = { inputDialogStateDrinks.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    drinksViewModel.removeDrink(currentDrink)
                    inputDialogStateDrinks.value = false }) {
                    Text("Delete")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }

    if(itemsList.isEmpty() && dessertList.isEmpty() && drinksList.isEmpty()) {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Start Creating Your Menu!", color = Color.Blue)
        }
    }


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
                            navController.navigate(RestoScreens.RestoSettingsScreen.name)
                        }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="back", tint = Color.Blue)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "Menu", color = Color.Blue, fontSize = 23.sp)
                }
            }

        }

        Box(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxSize()
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

                    Column() {
                        itemsList.forEach { item  ->
                            Log.d("", "ItemMENU:$itemsList ")
//                        swipetoDeleteItem(item = item, navController = navController, itemViewModel)

                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .combinedClickable(
                                    onLongClick = {
                                        currentItem = item
                                        inputDialogState.value = true
                                    },
                                    onClick = {
                                        navController.navigate(RestoScreens.UpdateScreen.name + "/${item.plateId}")
                                    }
                                )
//                            .pointerInput(Unit) {
//                                detectTapGestures(
//                                    onTap = {
//                                        navController.navigate(RestoScreens.UpdateScreen.name + "/${item.plateId}")
//                                    },
//                                    onLongPress = {
//                                        currentItem = item
//                                        inputDialogState.value = true
////                                        itemViewModel.removeItem(item)
//                                    }
//                                )
//                            }
//                            .clickable {
//                                navController.navigate(RestoScreens.UpdateScreen.name + "/${item.plateId}")
//                            }
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

                    Column() {
                        dessertList.forEach { item ->
                            Log.d("", "dessertMENU:$dessertList ")
//                        swipeToDeleteDessert(item = item, navController = navController, dessertViewModel)

                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .combinedClickable(
                                    onLongClick = {
                                        currentDessert = item
                                        inputDialogStateDessert.value = true

                                    },
                                    onClick = {
                                        navController.navigate(RestoScreens.DessertUpdateScreen.name + "/${item.dessertId}")

                                    }
                                )

//                            .clickable {
//                                navController.navigate(RestoScreens.DessertUpdateScreen.name + "/${item.dessertId}")
//
//                            }
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


                    Column() {
                        drinksList.forEach { item ->
                            Log.d("", "drinkMENU: $drinksList")
//                        swipetoDeleteDrink(item = item, navController = navController, drinksViewModel)

                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
//                            .pointerInput(Unit) {
//                                detectTapGestures(
//                                    onTap = {
//                                        navController.navigate(RestoScreens.DrinksUpdateScreen.name + "/${item.drinksId}")
//                                    },
//                                    onLongPress = {
//                                        currentDrink = item
//                                        Log.d("", "WHENPRESS:$currentDrink ")
//                                        inputDialogStateDrinks.value = true
////                                        drinksViewModel.removeDrink(item)
//                                    }
//                                )
//                            }
                                .combinedClickable(
                                    onLongClick = {
                                        currentDrink = item
                                        Log.d("", "WHENPRESS:$currentDrink ")
                                        inputDialogStateDrinks.value = true
                                    },
                                    onClick = {
                                        navController.navigate(RestoScreens.DrinksUpdateScreen.name + "/${item.drinksId}")
                                    }
                                )
//                            .clickable(
//                                onClick = {
//                                    navController.navigate(RestoScreens.DrinksUpdateScreen.name + "/${item.drinksId}")
//                                }
//                            )
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
    //////////////////////To check Ui LATER
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp, end = 5.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
                AddToMenuBtn {
                    navController.navigate(RestoScreens.CreatItemScreen.name)
                }
            }
///////////////////////////////////////

        }
        
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddToMenuBtn(onTap: () -> Unit) {
    FloatingActionButton(onClick = { onTap()
    },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add to Menu",
            tint = Color.White)
    }
}

//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun swipetoDeleteItem(item: MItem, navController: NavController, itemViewModel: ItemViewModel){
//
//    val state = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissValue.DismissedToStart) {
//                itemViewModel.removeItem(item)
//            }
//            true
//        }
//    )
//
//        SwipeToDismiss(
//        state = state,
//        background = {
//            val color = when (state.dismissDirection) {
//                DismissDirection.StartToEnd -> Color.Transparent
//                DismissDirection.EndToStart -> Color.Red
//                null -> Color.Transparent
//            }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = color)
//                    .padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.align(Alignment.CenterEnd)
//                )
//            }
//        },
//        dismissContent = {
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp, end = 10.dp)
//                .clickable {
//                    navController.navigate(RestoScreens.UpdateScreen.name + "/${item.plateId}")
//                }
//            ) {
//                Column() {
//
//                }
//                Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
//                    Row(modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
//                            Text(text = "${item.name}")
//                        }
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
//                            Text(text = "$ ${item.price}")
//                        }
//                    }
//                    Row() {
//                        Text(text = "${item.description}")
//                    }
//
//                }
//
//            }
//        },
//        directions = setOf(DismissDirection.EndToStart))
//    Divider()
//}
//
//
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun swipeToDeleteDessert(item: MItemDessert, navController: NavController, dessertViewModel: DessertViewModel){
//
//    val state = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissValue.DismissedToStart) {
//                dessertViewModel.removeDessert(item)
////                items.remove(item)
//            }
//            true
//        }
//    )
//
//    SwipeToDismiss(
//        state = state,
//        background = {
//            val color = when (state.dismissDirection) {
//                DismissDirection.StartToEnd -> Color.Transparent
//                DismissDirection.EndToStart -> Color.Red
//                null -> Color.Transparent
//            }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = color)
//                    .padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.align(Alignment.CenterEnd)
//                )
//            }
//        },
//        dismissContent = {
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp, end = 10.dp)
//                .clickable {
//                    navController.navigate(RestoScreens.UpdateScreen.name + "/${item.dessertId}")
//                }
//            ) {
//                Column() {
//
//                }
//                Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
//                    Row(modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
//                            Text(text = "${item.name}")
//                        }
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
//                            Text(text = "$ ${item.price}")
//                        }
//                    }
//                    Row() {
//                        Text(text = "${item.description}")
//                    }
//
//                }
//
//            }
//        },
//        directions = setOf(DismissDirection.EndToStart))
//    Divider()
//}
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun swipetoDeleteDrink(item: MItemDrinks, navController: NavController, drinksViewModel: DrinksViewModel){
//
//    val state = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissValue.DismissedToStart) {
//                drinksViewModel.removeDrink(item)
////                items.remove(item)
//            }
//            true
//        }
//    )
//
//    SwipeToDismiss(
//        state = state,
//        background = {
//            val color = when (state.dismissDirection) {
//                DismissDirection.StartToEnd -> Color.Transparent
//                DismissDirection.EndToStart -> Color.Red
//                null -> Color.Transparent
//            }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = color)
//                    .padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.align(Alignment.CenterEnd)
//                )
//            }
//        },
//        dismissContent = {
//            Card(modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp, end = 10.dp)
//                .clickable {
//                    navController.navigate(RestoScreens.UpdateScreen.name + "/${item.drinksId}")
//                }
//            ) {
//                Column() {
//
//                }
//                Column(modifier = Modifier.padding(start = 3.dp, end = 3.dp)) {
//                    Row(modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
//                            Text(text = "${item.name}")
//                        }
//                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
//                            Text(text = "$ ${item.price}")
//                        }
//                    }
//                    Row() {
//                        Text(text = "${item.description}")
//                    }
//
//                }
//
//            }
//        },
//        directions = setOf(DismissDirection.EndToStart))
//    Divider()
//}

//
//fun nose() {
//    SwipeToDismiss(
//        state = state,
//        background = {
//            val color = when (state.dismissDirection) {
//                DismissDirection.StartToEnd -> Color.Transparent
//                DismissDirection.EndToStart -> Color.Red
//                null -> Color.Transparent
//            }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = color)
//                    .padding(8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.align(Alignment.CenterEnd)
//                )
//            }
//        },
//        dismissContent = {
////            SampleItems(item)
//        },
//        directions = setOf(DismissDirection.EndToStart))
//
//}

// val platsList = mutableStateListOf<MItem>()
//            itemsList.forEach { item ->
//                platsList.add(item)
//            }
//
//            val dessertListNew = mutableStateListOf<MItemDessert>()
//            dessertList.forEach { item ->
//                dessertListNew.add(item)
//            }
//
//            val drinksListNew = mutableStateListOf<MItemDrinks>()
//            drinksList.forEach { item ->
//                drinksListNew.add(item)
//            }

//@Composable
//fun deleteDrink() {
//
////    var showDialog by remember { mutableStateOf(false) }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = {  },
//            title = {  },
//            text = { },
//            confirmButton = { },
//            dismissButton = { },
//        )
//    }
//}