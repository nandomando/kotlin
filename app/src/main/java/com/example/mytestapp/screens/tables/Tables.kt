package com.example.mytestapp.screens.tables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar
import com.example.mytestapp.components.itemButton
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MTable
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.repository.TableRepository
import com.example.mytestapp.ui.theme.Purple500
import com.example.mytestapp.viewModels.TableViewModel
import kotlin.math.log
import kotlin.math.roundToInt

@Composable
fun Tables(navController: NavController, tableViewModel: TableViewModel) {


    val tableList = tableViewModel.tableList.collectAsState().value

    val inputDialogState = remember { mutableStateOf(false) }

    if (inputDialogState.value) {
        CommonDialog(title = null, state = inputDialogState) {
            InputView(tableViewModel = tableViewModel)
        }
    }

//    if (inputDialogState.value) {
//        CommonDialogg(state = inputDialogState) {
//            InputView(tableViewModel = tableViewModel)
//        }
//    }


    Scaffold() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(15.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    //MultipleDraggableObject(letter = "A")
                }
            }
            Row() {
                tableList.forEach{
                    MultipleDraggableObject(letter = "${it.number}")
                }
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End) {
            AddTableBtn {
                inputDialogState.value = true
            }
            Spacer(modifier = Modifier.padding(15.dp))
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
                BottomNavBar(navController)
            }
        }
    }
}

@Composable
fun InputView(tableViewModel: TableViewModel) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Add Table Number", fontSize = 16.sp)
            Divider()
            val context = LocalContext.current

            Column() {
                var number = remember { mutableStateOf("") }

                val change: (String) -> Unit = { it ->
                    number.value = it  // it is supposed to be this
                }

                TextField(
                    value = number.value,
                    modifier = Modifier.padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = change,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    label = { Text(text = "") }

                )

                itemButton(modifier = Modifier.fillMaxWidth(),
                    text = "Save",
                    onClick = {
                        if (number.value.isNotEmpty()) {
                            tableViewModel.addTable(
                                MTable(
                                    number = number.value,
                                )
                            )
                            Toast.makeText(
                                context, "Table Added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

            }
        }
    }
}

@Composable
fun CommonDialog(title: String?,
                 state: MutableState<Boolean>,
                 content: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            state.value = false
        },
        title = title?.let {
            {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = title)
                    Divider(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        },
        text = content,
        dismissButton = {
            Button(onClick = { state.value = false }) {
                Text("Cancel")
            }
        },
        confirmButton = {
//            Button(onClick = { state.value = false }) {
//                Text("Ok")
//            }
        }, modifier = Modifier.padding(vertical = 8.dp)
    )
}


//    Column(modifier = Modifier.fillMaxSize()) {
//        Row(modifier = Modifier.fillMaxSize()) {
//                Column(
//                        modifier = Modifier
//                            .background(Color.White)
//                            .padding(15.dp)
//                            .fillMaxSize(),
//                        verticalArrangement = Arrangement.spacedBy(25.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        MultipleDraggableObject(letter = "A", bgColor = Color(0xFFFF0000))
//                        MultipleDraggableObject(letter = "B", bgColor = Color(0xFF00FF00))
//                        MultipleDraggableObject(letter = "C", bgColor = Color(0xFF0000FF))
//                        MultipleDraggableObject(letter = "D", bgColor = Color(0xFFFFF000))
//                        MultipleDraggableObject(letter = "E", bgColor = Color(0xFF00FFF0))
//                    }
//                }
//        }
//    AddTableBtn {}
//        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
//            BottomNavBar(navController)
//        }
//    }


@Composable
fun MultipleDraggableObject(letter: String) {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .pointerInput(Unit) {
                //Log.d("", "MultipleDraggableObject: $Unit")
                detectDragGestures { change, dragAmount ->
                    //Log.d("", "change: $change ")
                    change.consumeAllChanges()
                    //  Log.d("", "afterconsume: $change ")
                    // Log.d("", "dragamount:$dragAmount ")
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }

            .size(80.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            fontSize = 30.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}



////////////////////////////////////////////////////////////////
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTableBtn(onTap: () -> Unit) {
    FloatingActionButton(onClick = { onTap()
    },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Table",
            tint = Color.White)
    }
}
