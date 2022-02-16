package com.example.mytestapp.screens.home

import android.provider.ContactsContract
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mytestapp.components.*
import com.example.mytestapp.model.MItem
import java.util.*


@Preview
@Composable
fun Home(navController: NavController = NavController(context = LocalContext.current)){

    val listOfItems = listOf(
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", price = "80.50"),
        MItem(id = "cccccc", name = "tostadas de pollo", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", price = "40.26"),
        MItem(id = "cccccc", name = "crema batida", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "40.5"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm","75.21"),
        MItem(id = "cccccc", name = "pozole rojo", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "10"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "20"),
        MItem(id = "cccccc", name = "mole de morelos", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "70"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "120"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "90"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm", "48"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "pezcado a la plancha", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm"),
        MItem(id = "cccccc", name = "enchiladas rojas", description = "mejorejndjsnknskdnksnjnjdfnjdnsjnsnm")
    )

var currentItem by remember {
    mutableStateOf( MItem())
}

    ///
    Column(modifier = Modifier.fillMaxSize()) {
// bar to toggle between drinks, desserts, food, tables, and to go
        Row(modifier = Modifier) {
            Text(text = "homebaby", color = Color.Green)

        }
// list of items to send (scrawlable)
        Row(modifier = Modifier.weight(1f)) {
        // ListCard()
            VerticalScrollableComponent(listOfItems = listOfItems){
                // necesito pasar el item
                Log.d("TAG", "gridverticalscroll:$it ")
                currentItem = it
            }

        }
// this row items sended should be scrowlable
        Row(modifier = Modifier.weight(1f)) {
            Log.d("lineproduc", "lineproduct: $currentItem")
            ItemDisplayToCart(currentItem)
            //displayLazyCol(currentItem, {}, {})


        }

// nav bottom bar
        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController)
        }

    }

}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollableComponent(listOfItems: List<MItem>, onCardPressed: (MItem) -> Unit)  {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)) {
            items(listOfItems.size) {
                index ->
                ListCard( listOfItems[index]) {
                    onCardPressed(it)
                }
            }
        }
    }
}
