package com.tecsup.postulight.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tecsup.postulight.arduino.httpGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// --- Colores y Estilos Auxiliares ---
private val CorrectColor = Color(0xFF4CAF50) // Verde
private val IncorrectColor = Color(0xFFFF9800) // Naranja/Amarillo
private val LightBlueBackground = Color(0xFFE9F4F6) // Fondo azul claro de las tarjetas

@Composable
fun HomeScreen(ip: String) {

    // --- Lógica del Sensor (Asíncrona) ---
    var postureState by remember { mutableStateOf("Desconectado") }
    var timeCorrect by remember { mutableStateOf("0h 00m") }
    var timeIncorrect by remember { mutableStateOf("0h 00m") }
    var sensorOn by remember { mutableStateOf(false) } // Estado del interruptor (Iniciar/Detener)
    val scope = rememberCoroutineScope()

    // 💡 Toggle para Iniciar/Detener Sensor
    val toggleSensor: (Boolean) -> Unit = { isOn ->
        scope.launch(Dispatchers.IO) {
            val endpoint = if (isOn) "start" else "stop"
            val response = httpGet("$ip/$endpoint")
            if (!response.startsWith("ERROR")) {
                sensorOn = isOn
                // Opcional: mostrar un Toast de éxito o fallo
            } else {
                // Si falla, revertir el switch y mostrar error
                sensorOn = !isOn
                postureState = "Error: $response"
            }
        }
    }

    // 💡 Función para Obtener Datos (Refresh)
    val refreshData: () -> Unit = {
        scope.launch(Dispatchers.IO) {
            val response = httpGet("$ip/data")

            if (response.startsWith("ERROR")) {
                postureState = "Error: $response"
                timeCorrect = "0h 00m"
                timeIncorrect = "0h 00m"
            } else {
                // *** IMPLEMENTACIÓN REAL: Aquí debes parsear el JSON de tu Arduino ***
                // SIMULACIÓN DE PARSEO:
                if (response.contains("Correcta", ignoreCase = true) || response.contains("ok", ignoreCase = true)) {
                    postureState = "Correcta"
                } else {
                    postureState = "Mala"
                }
                timeCorrect = "3h 45m" // Valores de ejemplo para la UI
                timeIncorrect = "1h 30m" // Valores de ejemplo para la UI
            }
        }
    }
    // --- Fin Lógica del Sensor ---

    // UI Principal con scroll
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text("ESTADO ACTUAL", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 8.dp))

        // 1. Vincular Sensor y Lámpara (Switch)
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Vincular Sensor y Lámpara", fontWeight = FontWeight.Medium)
            Switch(checked = sensorOn, onCheckedChange = toggleSensor)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Tarjeta de Estado de Postura y Tiempos
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // Este es el nuevo componente de diseño que creamos
            PostureStatusCard(
                postureState = postureState,
                timeCorrect = timeCorrect,
                timeIncorrect = timeIncorrect,
                onRefreshClick = refreshData
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Tarjeta "Sobre la Cuenta"
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                Text("Sobre la Cuenta", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(16.dp))
                Divider()
                AccountOption(label = "Información personal", onClick = { /* Navegar */ })
                AccountOption(label = "Estadísticas Diarias", onClick = refreshData) // Ejemplo
                AccountOption(label = "Consejos", onClick = { /* Mostrar Consejos */ })
            }
        }

        // 4. Tarjeta "Notificaciones"
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Notificaciones", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("30 min con buena postura", color = Color.Gray)
            }
        }
    }
}

// --- Componentes Auxiliares para el Diseño del Estado ---

@Composable
fun PostureStatusCard(
    postureState: String,
    timeCorrect: String,
    timeIncorrect: String,
    onRefreshClick: () -> Unit
) {
    val isCorrect = postureState == "Correcta"
    val primaryColor = if (isCorrect) CorrectColor else IncorrectColor
    val icon = if (isCorrect) Icons.Default.Check else Icons.Default.Close

    // El padding total de la tarjeta
    Column(modifier = Modifier.padding(16.dp)) {

        // Fila que contiene el estado principal y los tiempos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Bloque Principal de Estado (Izquierda)
            Card(
                modifier = Modifier.height(180.dp).weight(0.5f).padding(end = 8.dp),
                colors = CardDefaults.cardColors(containerColor = LightBlueBackground),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Estado actual de la Postura", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)

                    // Icono Grande
                    Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Estado",
                            tint = Color.White,
                            modifier = Modifier
                                .size(90.dp)
                                .background(primaryColor, shape = CircleShape)
                                .padding(12.dp)
                        )
                    }

                    // Etiqueta de Estado/Botón de Refresco
                    Button(
                        onClick = onRefreshClick, // Usamos este botón para refrescar datos
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                    ) {
                        Text(postureState, color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // 2. Bloques de Tiempo (Derecha)
            Column(modifier = Modifier.weight(0.5f).height(180.dp)) {

                // Tiempo Correcto
                TimeDisplayCard(
                    title = "Tiempo en postura correcta",
                    time = timeCorrect,
                    modifier = Modifier.weight(1f).padding(bottom = 8.dp),
                    timeColor = Color.Black, // Texto negro para el diseño
                    bgColor = LightBlueBackground
                )

                // Tiempo Incorrecto
                TimeDisplayCard(
                    title = "Tiempo en mala postura",
                    time = timeIncorrect,
                    modifier = Modifier.weight(1f),
                    timeColor = Color.Black, // Texto negro para el diseño
                    bgColor = LightBlueBackground
                )
            }
        }
    }
}

// Componente para mostrar los paneles de tiempo
@Composable
fun TimeDisplayCard(
    title: String,
    time: String,
    modifier: Modifier = Modifier,
    timeColor: Color,
    bgColor: Color
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(time,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = timeColor
            )
        }
    }
}

// Componente para las opciones de cuenta (reutilizado de la sugerencia anterior)
@Composable
fun AccountOption(label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
            Text(label)
        }
        Icon(Icons.Default.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(16.dp))
    }
}