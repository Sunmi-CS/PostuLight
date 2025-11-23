package com.tecsup.postulight.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.postulight.R
import com.tecsup.postulight.navigation.NavRoutes

@Composable
fun WelcomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.posturalogo),
            contentDescription = "Logo",
            modifier = Modifier.size(350.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))  // Un poco más abajo el botón

        Button(
            onClick = { navController.navigate(NavRoutes.Login.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3BA6BD),  // Color del botón según la imagen
                contentColor = Color.White
            )
        ) {
            Text("Empezar ahora")
        }
    }
}
