package com.owch.owch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.owch.owch.navigation.NavItem
import com.owch.owch.navigation.Screen
import com.owch.owch.ui.screens.ChatScreen
import com.owch.owch.ui.screens.CreateDefectScreen
import com.owch.owch.ui.screens.DiaryScreen
import com.owch.owch.ui.screens.HomeScreen
import com.owch.owch.ui.screens.InspectAssetScreen
import com.owch.owch.ui.screens.InspectFormDetailsScreen
import com.owch.owch.ui.screens.InspectFormListScreen
import com.owch.owch.ui.screens.LocationScreen
import com.owch.owch.ui.screens.LoginScreen
import com.owch.owch.ui.screens.NotificationScreen
import com.owch.owch.ui.screens.SplashScreen
import com.owch.owch.ui.theme.OwchTheme
import com.owch.owch.viewModel.TokenViewModel
import com.owch.owch.viewModel.VehicleViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OwchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val vehicleViewModel: VehicleViewModel = viewModel()
                    val tokenViewModel: TokenViewModel = viewModel()

                    var showBottomSheet by remember { mutableStateOf(false) }
                    val sheetState =
                        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

                    val (valid, setValid) = remember { mutableStateOf(false) }

                    Scaffold(
                        bottomBar = { if (valid) BottomNavigationBar(navController = navController) }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                                NavigationGraph(
                                    navController = navController,
                                    vehicleViewModel = vehicleViewModel,
                                    tokenViewModel = tokenViewModel,
                                    setValid = setValid
                                )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        NavItem.Home,
        NavItem.Chat,
        NavItem.Inspect,
        NavItem.Location
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.screenRoute,
                onClick = { navController.navigate(screen.screenRoute) },
                icon = {
                    Image(
                        painterResource(id = screen.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        colorFilter = if (screen.screenRoute != "chat") ColorFilter.tint(Color(0xFF9797B3)) else null
                    )
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    vehicleViewModel: VehicleViewModel,
    tokenViewModel: TokenViewModel,
    setValid: (Boolean) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(NavItem.Home.screenRoute) { HomeScreen(navController, tokenViewModel) }
        composable(NavItem.Chat.screenRoute) { ChatScreen(navController) }
        composable(NavItem.Inspect.screenRoute) {
            InspectAssetScreen(
                navController,
                vehicleViewModel
            )
        }
        composable(NavItem.Location.screenRoute) { LocationScreen() }
        composable(Screen.FormListScreen.route) {
            val vehicleId = it.arguments?.getString("vehicleId") ?: ""
            InspectFormListScreen(navController, vehicleId)
        }
        composable(Screen.FormDetailsScreen.route) {
            val formId = it.arguments?.getString("formId") ?: ""
            InspectFormDetailsScreen(navController, formId)
        }
        composable(Screen.NotificationScreen.route) { NotificationScreen(navController) }
        composable(Screen.DiaryScreen.route) { DiaryScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen() }
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                setValid = setValid,
                navHostController = navController
            )
        }
    }
}