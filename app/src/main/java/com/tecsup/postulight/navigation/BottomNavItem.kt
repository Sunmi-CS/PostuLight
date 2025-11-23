package com.tecsup.postulight.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.BarChart // Recomiendo BarChart para estadísticas
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home_tab", Icons.Default.Home, "Inicio")
    object Statistics : BottomNavItem("statistics_tab", Icons.Default.BarChart, "Estadísticas")
    object Settings : BottomNavItem("settings_tab", Icons.Default.Settings, "Ajustes")


}