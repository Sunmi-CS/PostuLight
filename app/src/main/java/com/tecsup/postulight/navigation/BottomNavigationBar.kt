package com.tecsup.postulight.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    // 1. Definición de los ítems
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Statistics,
        BottomNavItem.Settings
    )

    // 2. Composable principal de la barra
    NavigationBar {
        // Obtiene la ruta actual para saber qué ítem resaltar como seleccionado
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            // 3. Ítem individual de la barra
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route, // Resalta el ítem si su ruta es la actual
                onClick = {
                    // Navega a la nueva ruta
                    navController.navigate(item.route) {
                        // Evita construir una pila de destinos de pestañas
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Evita múltiples copias del mismo destino
                        launchSingleTop = true
                        // Restaura el estado (scroll, etc.) al volver a un tab
                        restoreState = true
                    }
                }
            )
        }
    }
}