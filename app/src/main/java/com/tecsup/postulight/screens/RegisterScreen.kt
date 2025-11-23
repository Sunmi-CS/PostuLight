package com.tecsup.postulight.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.postulight.R
import com.tecsup.postulight.navigation.NavRoutes

@Composable
fun RegisterScreen(navController: NavController) {

    var selectedTab by remember { mutableStateOf("register") }
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }
    var showConfirmPass by remember { mutableStateOf(false) }


    val blue = Color(0xFF4AA5C9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()) // <-- aquí
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
 {

        Spacer(modifier = Modifier.height(40.dp))

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.posturalogo),
            contentDescription = "Logo",
            modifier = Modifier.size(260.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // TEXTOS
        Text(
            text = "Bienvenid@ !",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Crea una cuenta en PostuLight",
            fontSize = 14.sp,
            color = Color(0xFF777777),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // ------------------------------
        // PÍLDORA DE TABS (estilo Login)
        // ------------------------------
        val pillWidth = 300.dp
        val tabWidth = pillWidth / 2

        Box(
            modifier = Modifier
                .width(pillWidth)
                .height(46.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF3BA6BD))
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Iniciar Sesión
                Box(
                    modifier = Modifier
                        .width(tabWidth)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(if (selectedTab == "login") Color.White else Color.Transparent)
                        .clickable {
                            selectedTab = "login"
                            navController.navigate(NavRoutes.Login.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                // Registrarse
                Box(
                    modifier = Modifier
                        .width(tabWidth)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(if (selectedTab == "register") Color.White else Color.Transparent)
                        .clickable { selectedTab = "register" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Registrarse",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // ------------------------------
        // FORMULARIO DE REGISTRO CON ESTILO LOGIN
        // ------------------------------

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Nombre", color = Color(0xFFB0B0B0)) },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = blue,
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = lastname,
                onValueChange = { lastname = it },
                placeholder = { Text("Apellido", color = Color(0xFFB0B0B0)) },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = blue,
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Correo Electrónico", color = Color(0xFFB0B0B0)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Contraseña", color = Color(0xFFB0B0B0)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true,
            visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPass = !showPass }) {
                    Icon(
                        imageVector = if (showPass) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF8E8E93)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Confirmar Contraseña", color = Color(0xFFB0B0B0)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true,
            visualTransformation = if (showConfirmPass) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showConfirmPass = !showConfirmPass }) {
                    Icon(
                        imageVector = if (showConfirmPass) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF8E8E93)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        // ------------------------------
        // BOTÓN REGISTRARSE (estilo Login)
        // ------------------------------
        Button(
            onClick = {
                if (selectedTab == "register") {
                    navController.navigate(NavRoutes.Home.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "Registrarse",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
