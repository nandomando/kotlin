package com.example.mytestapp.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import coil.compose.ImagePainter
import com.example.mytestapp.components.*
import com.example.mytestapp.data.TableDataBaseDao
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.model.MTable
import com.example.mytestapp.viewModels.DessertViewModel
import com.example.mytestapp.viewModels.DrinksViewModel
import com.example.mytestapp.viewModels.ItemViewModel
import com.example.mytestapp.viewModels.TableViewModel
import com.google.rpc.context.AttributeContext
import io.grpc.internal.SharedResourceHolder


@Composable
fun Home(navController: NavController,
    itemViewModel: ItemViewModel,
         dessertViewModel: DessertViewModel,
         drinksViewModel: DrinksViewModel,
         tableViewModel: TableViewModel
){
    val tableList = tableViewModel.tableList.collectAsState().value
    val drinksList = drinksViewModel.drinksList.collectAsState().value
    val dessertList = dessertViewModel.dessertList.collectAsState().value
    val itemsList = itemViewModel.itemList.collectAsState().value

    var currentItem by remember { mutableStateOf( MItem())}
    var selectedTable by remember { mutableStateOf(MTable())}

    var expanded by remember { mutableStateOf(false) }
    var selectedTableText by remember { mutableStateOf("") }

    val checkedPlats = remember { mutableStateOf(true)}
    val checkedDessert = remember { mutableStateOf(false)}
    val checkedDrinks = remember { mutableStateOf(false)}


    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier. fillMaxWidth(),) {
            Column(modifier = Modifier
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = { expanded = !expanded }) {
                    //Icon(imageVector = icon, contentDescription = "")
                    Text(text = "Table: $selectedTableText", overflow = TextOverflow.Clip)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    //modifier = Modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})
                ) {
                    tableList.forEach { table ->
                        DropdownMenuItem(onClick = {
                            selectedTableText = table.number.toString()
                            expanded = false
                        }) {
                            //table.tableId
                            Text(text = table.number.toString())
                            val selectedTbl = produceState<MTable>(initialValue = MTable()) {
                                value = table
                            }
                            selectedTable = table
                        }
                    }
                }
                //dropDownMenu(suggestions = tableList)

                }
            Column(modifier = Modifier
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = {
                    checkedPlats.value = true
                    checkedDrinks.value = false
                    checkedDessert.value = false
                }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (checkedPlats.value) Color.LightGray else Color.Transparent
                )) {
                    Text(text = "Plats")
                }
            }
            Column(modifier = Modifier
                .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = {
                    checkedPlats.value = false
                    checkedDrinks.value = false
                    checkedDessert.value = true
                }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (checkedDessert.value) Color.LightGray else Color.Transparent
                )) {
                    Text(text = "Dessert")
                }
            }
            Column(modifier = Modifier
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = {
                    checkedPlats.value = false
                    checkedDrinks.value = true
                    checkedDessert.value = false
                }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (checkedDrinks.value) Color.LightGray else Color.Transparent
                )) {
                    Text(text = "Drinks")
                }

            }
            Column(modifier = Modifier
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = {

                }, colors = ButtonDefaults.textButtonColors(
                    // backgroundColor = if () Color.LightGray else Color.Transparent
                )) {
                    Text(text = "To Go")
                }
            }
        }
// list of items to send (scrawlable)
        if (checkedPlats.value) {
            Row(modifier = Modifier.weight(1f)) {
                VerticalScrollablePlatsComponent(itemsList){
                    // necesito pasar el item
                    Log.d("TAG", "verticalScrollItem:$it ")
                    currentItem = it
                    selectedTable.plats = it
                    tableViewModel.updateTable(selectedTable)

                    Log.d("", "selected table: ${selectedTable.plats}")
                    //Log.d("", "plats: ${currentItem}")
                }
            }
        }
        if (checkedDessert.value) {
            Row(modifier = Modifier.weight(1f)) {
                VerticalScrollableDessertComponent(dessertList){
                    // necesito pasar el item
                    Log.d("TAG", "verticalScrollDessert:$it ")
                    //currentItem.add(it)
                    //Log.d("", "dessert: ${currentItem}")

                }
            }

        }
        if (checkedDrinks.value) {
            Row(modifier = Modifier.weight(1f)) {
                VerticalScrollableDrinkComponent(drinksList){
                    // necesito pasar el item
                    Log.d("TAG", "verticalScrollItem:$it ")
                    //currentItem = it
                   // currentItem.add(it)
                }
            }
        }
// Row items sended should be scrowlable
        Row(modifier = Modifier.weight(1f)) {
            ItemDisplayToCart(selectedTable)
            //displayLazyCol(currentItem)

        }

// nav bottom bar
        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController)
        }

    }

}



/////////plats scrollable ////
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollablePlatsComponent(listOfItems: List<MItem>, onCardPressed: (MItem) -> Unit)  {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)) {
            items(listOfItems.size) {
                index ->
                ListPlatsCard( listOfItems[index]) {
                    onCardPressed(it)
                }
            }
        }
    }
}
//////////////////dessert scrollable ////////

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollableDessertComponent(listOfDesserts: List<MItemDessert>, onCardPressed: (MItemDessert) -> Unit)  {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)) {
            items(listOfDesserts.size) {
                    index ->
                ListDessertCard( listOfDesserts[index]) {
                    onCardPressed(it)
                }
            }
        }
    }
}
///////////////////drink scrollable///////
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollableDrinkComponent(listOfDrinks: List<MItemDrinks>, onCardPressed: (MItemDrinks) -> Unit)  {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)) {
            items(listOfDrinks.size) {
                    index ->
                ListDrinksCard( listOfDrinks[index]) {
                    onCardPressed(it)
                }
            }
        }
    }
}


////////////////////////////////////

//@Composable
//fun dropDownMenu(suggestions: List<MTable>) {
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedTableText by remember { mutableStateOf("") }
//
////    val icon = if (expanded)
////        Icons.Filled.KeyboardArrowUp
////    else
////        Icons.Filled.KeyboardArrowDown
//
//
//    Column(Modifier.fillMaxWidth()) {
//
//        TextButton(onClick = { expanded = !expanded }) {
//            //Icon(imageVector = icon, contentDescription = "")
//            Text(text = "Table: $selectedTableText", overflow = TextOverflow.Clip)
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            //modifier = Modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})
//        ) {
//            suggestions.forEach { table ->
//                DropdownMenuItem(onClick = {
//                    selectedTableText = table.number.toString()
//                    expanded = false
//                }) {
//                    //table.tableId
//                    Text(text = table.number.toString())
//                    val selectedTbl = produceState<MTable>(initialValue = MTable()) {
//                        value = table
//                    }
//                    //selectedTable = table
//                }
//            }
//        }
//    }

//}