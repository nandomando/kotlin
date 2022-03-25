package com.example.mytestapp.screens.home

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.room.ColumnInfo
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
import java.util.*
import kotlin.math.roundToInt


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


    var selectedTable by remember { mutableStateOf(MTable())}

    var totalPrice by remember { mutableStateOf( 0.0)}

    var expanded by remember { mutableStateOf(false) }
    var selectedTableText by remember { mutableStateOf("") }

    val checkedPlats = remember { mutableStateOf(true)}
    val checkedDessert = remember { mutableStateOf(false)}
    val checkedDrinks = remember { mutableStateOf(false)}

    val sended = remember { mutableStateOf(true)}





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

                            selectedTable = table

                        }) {
                            Text(text = table.number.toString())
//                            val selectedTbll = produceState<MTable>(initialValue = MTable()) {
//                                value = table
//                            }
                        }
                    }
                }
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
                    Log.d("", "BEFOREADDINGTable:$selectedTable ")

                    selectedTable.plats.add(it)
                    tableViewModel.updateTable(selectedTable)
                    selectedTable.plats.forEach{item ->
                        val price = item.price?.toFloat()

                        if (price != null) {
                            totalPrice += price
                        }
                    }

                }
            }
        }
        if (checkedDessert.value) {
            Row(modifier = Modifier.weight(1f)) {
                VerticalScrollableDessertComponent(dessertList){
                    selectedTable.desserts.add(it)
                    tableViewModel.updateTable(selectedTable)
                    selectedTable.desserts.forEach { item ->
                        val price = item.price?.toFloat()
                        if ( price != null) {
                            totalPrice += price
                        }
                    }
                }
            }

        }
        if (checkedDrinks.value) {
            Row(modifier = Modifier.weight(1f)) {
                VerticalScrollableDrinkComponent(drinksList){
                    selectedTable.drinks.add(it)
                    tableViewModel.updateTable(selectedTable)
                    selectedTable.drinks.forEach { item ->
                        val price = item.price?.toFloat()
                        if ( price != null) {
                            totalPrice += price
                        }
                    }
                }
            }
        }
// Row items sended should be scrowlable
        Row(modifier = Modifier.weight(1f)) {

            ItemsDisplayToCart(selectedTable, tableViewModel)

        }


        ////////////////////////////total/////////////
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(text = "Total:", fontWeight = FontWeight.Bold)
//            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                var tPrice = 0.00
                selectedTable.plats.forEach { item ->
                    tPrice += item.price?.toFloat()!!
                }
                selectedTable.desserts.forEach { item ->
                    tPrice += item.price?.toFloat()!!
                }
                selectedTable.drinks.forEach { item ->
                    tPrice += item.price?.toFloat()!!
                }
                Text(text = "Total:  $ ${tPrice.toFloat()}", fontWeight = FontWeight.Bold )
            }
        }
/////////////////buttones///////////////////////

        Row(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
            OutlinedButton(onClick = {

                selectedTable.plats.removeAll(selectedTable.plats)
                selectedTable.desserts.removeAll(selectedTable.desserts)
                selectedTable.drinks.removeAll(selectedTable.drinks)
                tableViewModel.updateTable(selectedTable)
            }, modifier = Modifier
                .weight(1f),
                border = BorderStroke(2.dp, color = MaterialTheme.colors.secondary),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.secondary,
//                backgroundColor = MaterialTheme.colors.secondary,
                )

//                elevation = ButtonDefaults.elevation(),
                //border = BorderStroke(2.dp, color = MaterialTheme.colors.primary),
            ) {
                Text(text = "Paye",
//                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.padding(2.dp))

            OutlinedButton(onClick = {

                val sendedPlatsList = mutableStateListOf<MItem>()
                selectedTable.plats.forEach { item ->
                    sendedPlatsList.add(item.copy(send = true))
                }

                val sendedDessertList = mutableStateListOf<MItemDessert>()
                selectedTable.desserts.forEach { item ->
                    sendedDessertList.add(item.copy(send = true))
                }


                val sendedDrinksList = mutableStateListOf<MItemDrinks>()
               selectedTable.drinks.forEach { item ->
                   sendedDrinksList.add(item.copy(send = true))
                }
                selectedTable.plats = sendedPlatsList
                selectedTable.desserts = sendedDessertList
                selectedTable.drinks = sendedDrinksList

                tableViewModel.updateTable(selectedTable)

            }, modifier = Modifier
                .weight(1f),
//                .background(color = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(50),
                border = BorderStroke(2.dp, Color.Blue),

//                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.primary)
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)

//                elevation = ButtonDefaults.elevation(),
               // border = BorderStroke(2.dp, color = MaterialTheme.colors.secondary),
            ) {
                Text(text = "Send",
//                    color = Color.Black
                )
            }
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