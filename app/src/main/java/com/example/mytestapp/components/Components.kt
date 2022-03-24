package com.example.mytestapp.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.model.MTable
import com.example.mytestapp.navigation.RestoNavigation
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.screens.tables.InputView
import com.example.mytestapp.viewModels.TableViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.selects.whileSelect
import java.util.*


@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)


}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value,
        onValueChange = { valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction)


}

@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icons.Default.Close

    }

}

/////////////////////////////////////////////// Bottom Nav Bar///////////////////////////////////////////
@Preview
@Composable
fun BottomNavBar(navController: NavController = NavController(context = LocalContext.current)) {
    Row(modifier = Modifier. fillMaxWidth()
        .padding(bottom = 0.dp)
        .height(28.dp),) {
        Column(modifier = Modifier.weight(1f),
            //.padding(bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "home",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.RestoHomeScreen.name)
                } .size(28.dp)
            )
        }
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Tables",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.TablesScreen.name)
                } .size(28.dp)
            )
        }
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.RestoSettingsScreen.name)
                } .size(28.dp)
            )
        }


    }
}

//////////////////plats list card//////////////
@Composable
fun ListPlatsCard(item: MItem,
    onPressDetails:(currentPlat: MItem) -> Unit)
    {
      Card(shape = RoundedCornerShape(15.dp),
      backgroundColor = Color.White,
      elevation = 6.dp,
      modifier = Modifier
          .padding(5.dp)
          .height(100.dp)
          .width(100.dp)
          .clickable {
              onPressDetails.invoke(item)

          }) {
          Column(modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "${item.name}",
              overflow = TextOverflow.Visible)
          }
      }
}
//////////////////dessert list card//////////////
@Composable
fun ListDessertCard(dessert: MItemDessert,
             onPressDetails:(currentDessert: MItemDessert) -> Unit)
{
    Card(shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(5.dp)
            .height(100.dp)
            .width(100.dp)
            .clickable {
                onPressDetails.invoke(dessert)

            }) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${dessert.name}",
                overflow = TextOverflow.Visible)
        }
    }
}
//////////////////drinks list card//////////////
@Composable
fun ListDrinksCard(drink: MItemDrinks,
             onPressDetails:(currentDrink: MItemDrinks) -> Unit)
{
    Card(shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(5.dp)
            .height(100.dp)
            .width(100.dp)
            .clickable {
                onPressDetails.invoke(drink)

            }) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${drink.name}",
                overflow = TextOverflow.Visible)
        }
    }
}







///////////funtion to display nameitem for cost /////////////////////////////////////////////////////////////////

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsDisplayToCart (tableItems:  MTable, tableViewModel: TableViewModel) {

    val scrollState = rememberScrollState()

    var currentItem  by remember { mutableStateOf(MItem())}
    var currentDessert by remember { mutableStateOf(MItemDessert())}
    var currentDrink by remember { mutableStateOf(MItemDrinks())}


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
                    tableItems.plats.remove(currentItem)
                    tableViewModel.updateTable(tableItems)
                    inputDialogState.value = false }) {
                    Text("Delete")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }
/////////////////////////////////POP UP DELETE DESSERTS/////////////
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

                    tableItems.desserts.remove(currentDessert)
                    tableViewModel.updateTable(tableItems)

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
                    tableItems.drinks.remove(currentDrink)
                    tableViewModel.updateTable(tableItems)

                    inputDialogStateDrinks.value = false }) {
                    Text("Delete")
                }
            }, modifier = Modifier.padding(vertical = 8.dp)
        )
    }

    Log.d("", "listTableItemsBEFORE: $tableItems")

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Column(modifier = Modifier.fillMaxSize()) {
            tableItems.plats?.distinct()?.forEach { item ->

                val columnColor = if (item.send) Color.Blue else Color.White

                Column(modifier = Modifier
                    .background(color = columnColor)
                    .fillMaxWidth()
                    .clickable {
                        currentItem = item
                        inputDialogState.value = true
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.name + " x" + Collections.frequency(tableItems.plats, item))
                        }
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Text(text = "$ ${item.price?.toFloat()
                                ?.times(Collections.frequency(tableItems.plats, item))}")
                        }
                    }
                }
            }
        }
        ////////////////dessert///
        Column(modifier = Modifier.fillMaxSize()) {
            tableItems.desserts?.distinct()?.forEach { item ->

                val columnColor = if (item.send) Color.Blue else Color.White

                Column(modifier = Modifier
                    .background(color = columnColor)
                    .fillMaxWidth()
                    .clickable {
                        currentDessert = item
                        inputDialogStateDessert.value = true
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.name + " x" + Collections.frequency(tableItems.desserts, item))
                        }
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Text(text = "$ ${item.price?.toFloat()
                                ?.times(Collections.frequency(tableItems.desserts, item))}")
                        }
                    }
                }
            }
        }

        ////////////////drinks///
        Column(modifier = Modifier.fillMaxSize()) {
            tableItems.drinks.distinct()
                .forEach { item ->

                val columnColor = if (item.send) Color.Blue else Color.White


                    Column(modifier = Modifier
                        .background(color = columnColor)
                        .fillMaxWidth()
                        .clickable {
                            currentDrink = item
                            inputDialogStateDrinks.value = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.name + " x" + Collections.frequency(tableItems.drinks, item))
                        }
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Text(text = "$ ${item.price?.toFloat()
                                ?.times(Collections.frequency(tableItems.drinks, item))}")
                        }
                    }
                }
            }
        }
        //////////////////////////test/////////////////////not send drinks
//        Column(modifier = Modifier.fillMaxSize()) {
//            tableItems.drinks
//                .forEach { item ->
//
//                    Log.d("list", "ItemsDisplayToCart: $item")
//                    val columnColor = if (item.send) Color.Blue else Color.White
//
//                    if (!item.send){
//                        Column(modifier = Modifier
//                            .background(color = columnColor)
//                            .fillMaxWidth()
//                            .clickable {
//                                currentDrink = item
//                                inputDialogStateDrinks.value = true
//                            }
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(start = 15.dp, end = 15.dp)
//                            ) {
//                                Column(modifier = Modifier.weight(1f)) {
//                                    Text(text = item.name + " x" + Collections.frequency(tableItems.drinks, item))
//                                }
//                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
//                                    Text(text = "$ ${item.price?.toFloat()
//                                        ?.times(Collections.frequency(tableItems.drinks, item))}")
//                                }
//                            }
//                        }
//                    }
//                    }
//
//        }
////////////////////////
    }
}

////////////////////////////////////////////////////////////

//val inputDialogState = remember { mutableStateOf(false) }
//
//if (inputDialogState.value) {
//    deleteDialog(title = null, state = inputDialogState) {
//        DeleteView(tableViewModel = tableViewModel)
//    }
//}

////////////////////////////////////DELETE PLATE NAME /////////////////////
@Composable
fun DeleteViewPlate(currentItem: MItem) {

    val number = remember { mutableStateOf("") }

//    val change: (String) -> Unit = { it ->
//        number.value = it  // it is supposed to be this
//    }

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your about to Delete: 1 ${currentItem.name} ", fontSize = 16.sp, color = Color.Red)
            Divider()
            //val context = LocalContext.current

//            Column() {
//
//                TextField(
//                    value = number.value,
//                    modifier = Modifier.padding(
//                        top = 9.dp,
//                        bottom = 8.dp
//                    ),
//                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                    onValueChange = change,
//                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//                    label = { Text(text = "") }
//
//                )
//
////                itemButton(modifier = Modifier.fillMaxWidth(),
////                    text = "Save",
////                    onClick = {
////                        if (number.value.isNotEmpty()) {
////                            tableViewModel.addTable(
////                                MTable(
////                                    number = number.value,
////                                )
////                            )
////                            Toast.makeText(
////                                context, "Table Added",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    })
//
//            }
        }
    }
}

//////////////////////////////////////DELETE DESSERT NAME /////////////////////


@Composable
fun DeleteViewDessert(currentItem: MItemDessert) {

    val number = remember { mutableStateOf("") }

//    val change: (String) -> Unit = { it ->
//        number.value = it  // it is supposed to be this
//    }

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your about to Delete: 1 ${currentItem.name} ", fontSize = 16.sp, color = Color.Red)
            Divider()
            //val context = LocalContext.current

//            Column() {
//
//                TextField(
//                    value = number.value,
//                    modifier = Modifier.padding(
//                        top = 9.dp,
//                        bottom = 8.dp
//                    ),
//                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                    onValueChange = change,
//                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//                    label = { Text(text = "") }
//
//                )
//
////                itemButton(modifier = Modifier.fillMaxWidth(),
////                    text = "Save",
////                    onClick = {
////                        if (number.value.isNotEmpty()) {
////                            tableViewModel.addTable(
////                                MTable(
////                                    number = number.value,
////                                )
////                            )
////                            Toast.makeText(
////                                context, "Table Added",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    })
//
//            }
        }
    }
}
//////////////////////////////////////DELETE DRINKS NAME ///////////////


@Composable
fun DeleteViewDrink(currentItem: MItemDrinks) {

    val number = remember { mutableStateOf("") }

//    val change: (String) -> Unit = { it ->
//        number.value = it  // it is supposed to be this
//    }

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your about to Delete: 1 ${currentItem.name} ", fontSize = 16.sp, color = Color.Red)
            Divider()
            //val context = LocalContext.current

//            Column() {
//
//                TextField(
//                    value = number.value,
//                    modifier = Modifier.padding(
//                        top = 9.dp,
//                        bottom = 8.dp
//                    ),
//                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                    onValueChange = change,
//                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//                    label = { Text(text = "") }
//
//                )
//
////                itemButton(modifier = Modifier.fillMaxWidth(),
////                    text = "Save",
////                    onClick = {
////                        if (number.value.isNotEmpty()) {
////                            tableViewModel.addTable(
////                                MTable(
////                                    number = number.value,
////                                )
////                            )
////                            Toast.makeText(
////                                context, "Table Added",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    })
//
//            }
        }
    }
}

//@Composable
//fun deleteDialog(title: String?,
//                 state: MutableState<Boolean>,
//                 tableViewModel: TableViewModel,
//                 content: @Composable (() -> Unit)? = null
//) {
//    AlertDialog(
//        onDismissRequest = {
//            state.value = false
//        },
//        title = title?.let {
//            {
//                Column(
//                    Modifier.fillMaxWidth(),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Text(text = title)
//                    Divider(modifier = Modifier.padding(bottom = 8.dp))
//                }
//            }
//        },
//        text = content,
//        dismissButton = {
//            Button(onClick = { state.value = false }) {
//                Text("Cancel")
//            }
//        },
//        confirmButton = {
//            Button(onClick = {
//                //tableViewModel.updateTable()
//                state.value = false }) {
//                Text("Delete")
//            }
//        }, modifier = Modifier.padding(vertical = 8.dp)
//    )
//}

//////////////////////display lazy col test
//@Composable
//fun displayLazyCol (
//    itemsOrdered: MutableList<MItem>?,
////    onAddNote: (MItem) -> Unit,
////    onRemoveNote: (MItem) -> Unit
//) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        LazyColumn{
//            if (itemsOrdered != null) {
//                items(itemsOrdered.size) {
//                        index ->
//                    itemRow(item = itemsOrdered[index], onCurrentItemClicked = {})
//                    Log.d("lazycol", "displayLazyCol: $itemsOrdered")
//                }
//            }
////            item(itemOrdered){ item ->
////                itemRow(item = itemOrdered, onCurrentItemClicked = {})
//////                itemRow(item = ,
//////                    //onNoteClicked = { onRemoveNote(it) }
//////             )
////            }
//        }
//    }
//}




//////////item row card

//@Composable
//fun itemRow(
//    modifier: Modifier = Modifier,
//    item: MItem,
//    onCurrentItemClicked: (Any) -> Unit) {
//    Surface(
//        modifier
//            .padding(4.dp)
//            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
//            .fillMaxWidth(),
//        color = Color(0xFFDFE6EB),
//        elevation = 6.dp) {
//        Column(modifier
//            .clickable { onCurrentItemClicked(item) }
//            .padding(horizontal = 14.dp, vertical = 6.dp),
//            horizontalAlignment = Alignment.Start) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(text = "${item.name}")
//                Log.d("itemrow", "itemRow: ${item}")
//            }
//            Column(modifier = Modifier.weight(1f),) {
//                Text(text = "${item.price}")
//            }
//
//        }
//
//
//    }
//
//}

@ExperimentalComposeUiApi
@Composable
fun itemInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = modifier
    )

}

@Composable
fun itemButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier) {
        Text(text)

    }

}

//
//@ExperimentalComposeUiApi
//@Composable
//fun itemInputInt(
//    modifier: Modifier = Modifier,
//    text: Int,
//    label: String,
//    maxLine: Int = 1,
//    onTextChange: (String) -> Unit,
//    onImeAction: () -> Unit = {}
//) {
//    val keyboardController = LocalSoftwareKeyboardController.current
//    var text by remember {
//        mutableStateOf("")
//    }
//
//    TextField(
//        value = text,
//        onValueChange = {value ->
//            if(value.length <= 4) {
//                text = value.filter { it.isDigit() }
//            }
//        },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = Color.Transparent),
//        maxLines = maxLine,
//        label = { Text(text = label)},
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = ImeAction.Done),
//        keyboardActions = KeyboardActions(onDone = {
//            onImeAction()
//            keyboardController?.hide()
//
//        }),
//        modifier = modifier
//    )
//
//}


///////////////// example of toggle buttons ////////////

@Composable
fun MultiToggleButton(
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: (String) -> Unit
) {
    val selectedTint = MaterialTheme.colors.primary
    val unselectedTint = Color.Unspecified

    Row(modifier = Modifier
        .height(IntrinsicSize.Min)
        .border(BorderStroke(1.dp, Color.LightGray))) {
        toggleStates.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection.lowercase() == toggleState.lowercase()
            val backgroundTint = if (isSelected) selectedTint else unselectedTint
            val textColor = if (isSelected) Color.White else Color.Unspecified

            if (index != 0) {
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
            }

            Row(
                modifier = Modifier
                    .background(backgroundTint)
                    .padding(vertical = 6.dp, horizontal = 8.dp)
                    .toggleable(
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }
                        })
            ) {
                Text(toggleState.toCapital(), color = textColor, modifier = Modifier.padding(4.dp))
            }

        }
    }
}

fun String.toCapital(): String {
    return this.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault())} }

//////////////////////////



////////////////////////////////swipeable//////////////
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun ItemDisplayToCart (tableItems:  MTable, tableViewModel: TableViewModel ) {
//    val scrollState = rememberScrollState()
//
//    Log.d("", "listTableItemsBEFORE: $tableItems")
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .verticalScroll(scrollState)) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            tableItems.plats?.distinct()?.forEach { item ->
//
//                val state = rememberDismissState(
//                    confirmStateChange = {
//                        if (it == DismissValue.DismissedToStart) {
//                            tableItems.plats.remove(item)
//                            tableViewModel.updateTable(tableItems)
//                        }
//                        true
//                    }
//                )
//
//                SwipeToDismiss(
//                    state = state,
//                    background = {
//                        val color = when (state.dismissDirection) {
//                            DismissDirection.StartToEnd -> Color.Transparent
//                            DismissDirection.EndToStart -> Color.Red
//                            null -> Color.Transparent
//                        }
//
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(color = color)
//                                .padding(8.dp)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Delete,
//                                contentDescription = "Delete",
//                                tint = Color.White,
//                                modifier = Modifier.align(Alignment.CenterEnd)
//                            )
//                        }
//                    },
//                    dismissContent = {
//                        // item
//                        Column(modifier = Modifier.fillMaxWidth()) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(start = 15.dp, end = 15.dp)
//                            ) {
//                                Column(modifier = Modifier.weight(1f)) {
//                                    Text(text = item.name + " x" + Collections.frequency(tableItems.plats, item))
//                                }
//                                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
//                                    Text(text = "$ ${item.price?.toInt()
//                                        ?.times(Collections.frequency(tableItems.plats, item))}")
//                                }
//                            }
//                        }
//
//                    },
//                    directions = setOf(DismissDirection.EndToStart)
//                )
//
//                Log.d("", "ListTableItems: $item")
//
//
//            }
//        }
//
//    }
//}