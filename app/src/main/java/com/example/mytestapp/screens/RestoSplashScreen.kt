package com.example.mytestapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mytestapp.R
import com.example.mytestapp.navigation.RestoNavigation
import com.example.mytestapp.navigation.RestoScreens
import com.example.mytestapp.screens.login.RestoLoginScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Preview
@Composable
fun RestoSplashScreen(navController: NavController = NavController(context = LocalContext.current)) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true ) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(2000L)

        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(RestoScreens.LoginScreen.name)
        }else {
            navController.navigate(RestoScreens.RestoHomeScreen.name)
        }
    }

    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Image(painter = painterResource(id = R.drawable.logoresto ), contentDescription = "", modifier = Modifier.scale(scale.value))
    }
}
