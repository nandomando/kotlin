package com.example.mytestapp.screens.settings

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytestapp.components.BottomNavBar
import com.example.mytestapp.navigation.RestoScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun RestoSettings(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.weight(1f))
         {
             Column(modifier = Modifier
                 .background(Color.Gray)
             ) {
                 Text(text = "SETTINGS", color = Color.Green)
             }
        }

        Row(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier
                .fillMaxSize()
//                .background(Color.LightGray)
            ) {
                   Surface(modifier = Modifier
                       .padding(4.dp)
                       .clip(
                           RoundedCornerShape(
                               topStart = 33.dp,
                               topEnd = 33.dp,
                               bottomStart = 33.dp,
                               bottomEnd = 33.dp
                           )
                       )
                       .fillMaxSize(),
                    color = Color(0xFF92CBDF),
                    elevation = 6.dp) {

                       Column(modifier = Modifier
                           .fillMaxSize()
                           .padding(top = 15.dp, start = 10.dp)) {

                           Row(modifier = Modifier.fillMaxWidth()
                               .clickable {
                                   navController.navigate(RestoScreens.CreatItemScreen.name)
                               }
                           ) {
                               Text(text = "Add Item to Menu",
                                   modifier = Modifier)
                           }

                           bottomBlackPadding()

                           Row(modifier = Modifier.fillMaxWidth()
                               .clickable {
                                   navController.navigate(RestoScreens.DetailScreen.name)
                               }
                           ) {
                               Text(text = "Edit Menu",
                                   modifier = Modifier)
                           }
                           bottomBlackPadding()

                           Row() {
                               Text(text = "Log Out", modifier = Modifier.clickable {
                                   FirebaseAuth.getInstance().signOut().run {
                                       navController.navigate(RestoScreens.LoginScreen.name)
                                   }
                               })
                           }
                           bottomBlackPadding()
                       }
                   }

            }
        }

        Row(modifier = Modifier, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController)
        }

    }

}

@Composable
fun bottomBlackPadding() {
    Divider(modifier = Modifier,
        color = Color.Black,
        thickness = 1.dp,
//        startIndent = 22.dp,
    )
}