package com.tecsup.postulight.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.postulight.screens.*

@Composable
fun AppNavigation(ip: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Welcome.route
    ) {

        composable(NavRoutes.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(NavRoutes.Login.route) {
            LoginScreen(navController)
        }

        composable(NavRoutes.Register.route) {
            RegisterScreen(navController)
        }

        composable(NavRoutes.Home.route) {
            HomeScreen(ip)
        }
    }
}

