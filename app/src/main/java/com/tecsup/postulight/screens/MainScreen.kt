package com.tecsup.postulight.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.postulight.R
import com.tecsup.postulight.navigation.BottomNavItem
import com.tecsup.postulight.navigation.BottomNavigationBar
import com.tecsup.postulight.navigation.NavRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(ip: String, navController: NavController) {

    // NavController exclusivo para BottomNavigation
    val bottomNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "CONFIGURACIÓN",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()

                NavigationDrawerItem(
                    label = { Text("Información Personal") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        bottomNavController.navigate("personal_info")
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Ajustes") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        bottomNavController.navigate(BottomNavItem.Settings.route)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Cerrar sesión") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(NavRoutes.Login.route) { popUpTo(0) }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                Surface(
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.tu_imagen),
                                contentDescription = "Foto usuario",
                                modifier = Modifier
                                    .size(55.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color(0xFFE0E0E0), CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                "Hola, Alexandra",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    }
                }
            },

            bottomBar = { BottomNavigationBar(navController = bottomNavController) }
        ) { paddingValues ->
            NavHost(
                navController = bottomNavController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("personal_info") { PersonalInfoScreen() }
                composable(BottomNavItem.Home.route) { HomeScreen(ip = ip, navController = bottomNavController) }
                composable(BottomNavItem.Statistics.route) { StatisticsScreen() }
                composable("tips") { TipsScreen() }
                composable(BottomNavItem.Settings.route) { SettingsScreen() }
            }
        }
    }
}

