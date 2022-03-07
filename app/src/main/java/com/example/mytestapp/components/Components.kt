package com.example.mytestapp.components

import android.util.Log
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

///////////////////////////////////////////////my test of bottom nav bar

@Composable
fun BottomNavBar(navController: NavController = NavController(context = LocalContext.current)) {
    Row(modifier = Modifier. fillMaxWidth(),) {
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "home",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.RestoHomeScreen.name)
                }
            )
        }
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Tables",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.TablesScreen.name)
                }
            )
        }
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.RestoSettingsScreen.name)
                }
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




















/////////////////////////////////////card to display item and price
@Composable
fun itemCardPrice () {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "")
            }
            Column(modifier = Modifier.weight(1f),) {
                Text(text = "")
            }
        }
    }
}

///////////funtion to display nameitem for cost

@Composable
fun ItemDisplayToCart (table: MTable ) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "${table.plats?.name}")
                    }
                    Column(modifier = Modifier.weight(1f),) {
                        Text(text = "${table.plats?.price}")
                    }
                }
            }
        }

    }
}

//////////////////////display lazy col test
@Composable
fun displayLazyCol (
    itemsOrdered: List<Any>,
//    onAddNote: (MItem) -> Unit,
//    onRemoveNote: (MItem) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn{
            items(itemsOrdered.size) {
                index ->
                itemRow(item = itemsOrdered[index], onCurrentItemClicked = {})
                Log.d("lazycol", "displayLazyCol: $itemsOrdered")
            }
//            item(itemOrdered){ item ->
//                itemRow(item = itemOrdered, onCurrentItemClicked = {})
////                itemRow(item = ,
////                    //onNoteClicked = { onRemoveNote(it) }
////             )
//            }
        }
    }
}




//////////item row card

@Composable
fun itemRow(
    modifier: Modifier = Modifier,
    item: Any,
    onCurrentItemClicked: (Any) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp) {
        Column(modifier
            .clickable { onCurrentItemClicked(item) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${item}")
                Log.d("itemrow", "itemRow: ${item}")
            }
            Column(modifier = Modifier.weight(1f),) {
                Text(text = "${item}")
            }

        }


    }

}

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
