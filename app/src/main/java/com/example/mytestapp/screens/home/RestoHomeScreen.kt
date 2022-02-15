package com.example.mytestapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar


//@Preview
@Composable
fun Home(navController: NavController = NavController(context = LocalContext.current)){
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.weight(1f)) {
            Text(text = "homebaby", color = Color.Green)

        }

        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController)
        }

    }

}

@Composable
fun CurrentTable(items: List<Mitems>, navController: NavController) {

}