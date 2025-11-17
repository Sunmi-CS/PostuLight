package com.tecsup.postulight.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.postulight.arduino.httpGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(ip: String) {

    var data by remember { mutableStateOf("Sin datos") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            scope.launch(Dispatchers.IO) { httpGet("$ip/start") }
        }) { Text("Iniciar Sensor") }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.IO) { httpGet("$ip/stop") }
        }) { Text("Detener Sensor") }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                val response = httpGet("$ip/data")
                data = response
            }
        }) { Text("Obtener Datos") }

        Spacer(Modifier.height(30.dp))

        Text("Respuesta del sensor:")
        Text(data)
    }
}
