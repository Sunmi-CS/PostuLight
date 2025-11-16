package com.tecsup.postulight.navigation

sealed class NavRoutes(val route: String) {
    object Welcome: NavRoutes("welcome")
    object Login: NavRoutes("login")
    object Register: NavRoutes("register")
}
