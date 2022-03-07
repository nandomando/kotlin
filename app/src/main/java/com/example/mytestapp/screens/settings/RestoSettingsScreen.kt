package com.example.mytestapp.screens.settings

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar
import com.example.mytestapp.navigation.RestoScreens
import com.google.firebase.auth.FirebaseAuth

@Preview
@Composable
fun RestoSettings(navController: NavController = NavController(context = LocalContext.current)){
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .weight(1f)
            .background(Color.Gray)
            .fillMaxWidth(),)
         {
            Text(text = "SETTINGS", color = Color.Green)

            IconButton( onClick = {

                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(RestoScreens.LoginScreen.name)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "logout",)
            }

        }
        Text(text = "edit Item", color =
        Color.Red,
            modifier = Modifier.clickable {
            navController.navigate(RestoScreens.CreatItemScreen.name)
        })


        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController)
        }

    }

}