package com.example.mytestapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.navigation.RestoNavigation
import com.example.mytestapp.navigation.RestoScreens


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
@Preview
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
                contentDescription = "Detail",
                modifier = Modifier.clickable {
                    navController.navigate(RestoScreens.DetailScreen.name)
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

/////////////////////////navbarcomponent
//@Preview
//@Composable
//fun NavBarComposable () {
//    val navController = rememberNavController()
//
//    val navigationItems = listOf(
//        Destinations.Pantalla1,
//        Destinations.Pantalla2,
//        Destinations.Pantalla3
//    )
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) },
////        floatingActionButton = { FloatingActionButton(onClick = {}) {
////            Icon(imageVector = Icons.Default.Add, contentDescription = "Fab Icon")
////        } },
////        isFloatingActionButtonDocked = false,
////        floatingActionButtonPosition = FabPosition.End
//    ){
//        //NavigationHost(navController)
//        navController
//    }
//}

/////////////////////////////////////

//@Composable
//fun BottomNavigationBar(
//    navController: NavHostController,
//    items: List<Destinations>
//) {
//    val currentRoute = currentRoute(navController)
//
//    BottomNavigation(
//        backgroundColor = Color(0.0f, 0.8f, 0.8f),
//        contentColor = Color.White
//    ) {
//        items.forEach { screen ->
//            BottomNavigationItem(
//                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
//                label = { Text(screen.title) },
//                selected = currentRoute == screen.route,
//                onClick = {
//                    navController.navigate(screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id){
//                            saveState = true
//                        }
//
//                        launchSingleTop = true
//                    }
//                },
//                alwaysShowLabel = true
//            )
//        }
//    }
//}
//
//@Composable
//private fun currentRoute(navController: NavHostController): String? {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    return navBackStackEntry?.destination?.route
//}
//
////////////////////////////////////////
//
//sealed class Destinations(
//    val route: String,
//    val title: String,
//    val icon: ImageVector
//) {
//    object Pantalla1: Destinations(RestoScreens.RestoHomeScreen.name, "Pantalla 1", Icons.Filled.Home)
//    object Pantalla2: Destinations(RestoScreens.DetailScreen.name, "Pantalla 2", Icons.Filled.Settings)
//    object Pantalla3: Destinations(RestoScreens.SearchScreen.name, "Pantalla 3", Icons.Filled.Favorite)
//}
