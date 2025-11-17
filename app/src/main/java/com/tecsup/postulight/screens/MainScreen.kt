package com.tecsup.postulight.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.postulight.navigation.BottomNavItem
import com.tecsup.postulight.navigation.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(ip: String) {
    // Este NavController maneja la navegación *entre las pestañas*
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hola, Alex") }, // Título fijo o dinámico
                actions = {
                    IconButton(onClick = { /* Lógica para abrir menú lateral */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) } // Barra de navegación
    ) { paddingValues ->
        // Contenido de las pestañas
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // 1. Pestaña de Inicio (El Dashboard con la lógica de tu sensor)
            composable(BottomNavItem.Home.route) {
                // La función HomeScreen debe ser refactorizada para mostrar el nuevo diseño.
                HomeScreen(ip)
            }
            // 2. Pestaña de Estadísticas
            composable(BottomNavItem.Statistics.route) {
                StatisticsScreen()
            }
            // 3. Pestaña de Ajustes
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }
    }
}