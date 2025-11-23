package com.tecsup.postulight.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Tus colores
private val GoodColor = Color(0xFF3BA6BD)     // Verde-azulado
private val BadColor = Color(0xFFE67A4D)      // Naranja suave
private val CardBackground = Color(0xFFF3F3F3)

@Composable
fun StatisticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ⭐ FONDO BLANCO EN TODA LA PANTALLA
            .verticalScroll(rememberScrollState())

            .padding(20.dp)
    ) {

        // ---------------- TÍTULO ----------------
        Text(
            "ESTADÍSTICAS",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------------- RESUMEN DEL DÍA ----------------
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Text("Resumen del Día", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Text(
                    "Hoy, 20 de Diciembre",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

                // --- BARRA DIVIDIDA ---
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(50))
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.85f)
                            .fillMaxHeight()
                            .background(GoodColor, RoundedCornerShape(50))
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.15f)
                            .fillMaxHeight()
                            .background(BadColor, RoundedCornerShape(50))
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Indicadores
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Canvas(modifier = Modifier.size(12.dp)) {
                            drawCircle(GoodColor)
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("85% Buena Postura", color = Color.Black)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Canvas(modifier = Modifier.size(12.dp)) {
                            drawCircle(BadColor)
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("15% Mala Postura", color = Color.Black)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    "Resumen Semanal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "17/11/25 - 23/11/25",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                WeeklyPostureChart(
                    data = listOf(
                        85f to 15f, // Lunes
                        70f to 30f, // Martes
                        60f to 40f, // Miércoles
                        75f to 25f, // Jueves
                        80f to 20f, // Viernes
                        90f to 10f, // Sábado
                        50f to 50f  // Domingo
                    ),
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                )
            }
        }


        Spacer(modifier = Modifier.height(25.dp))

        // ---------------- LÍNEA DE TIEMPO ----------------
        Text(
            "LÍNEA DE TIEMPO",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(modifier = Modifier.padding(20.dp)) {

                TimelineItem(
                    text = "Buena postura",
                    time = "09:00",
                    color = GoodColor,
                    icon = Icons.Default.Check
                )

                TimelineItem(
                    text = "Mala postura detectada",
                    time = "10:45",
                    color = BadColor,
                    icon = Icons.Default.Close
                )

                TimelineItem(
                    text = "Mala postura detectada",
                    time = "11:15",
                    color = BadColor,
                    icon = Icons.Default.Close
                )

                TimelineItem(
                    text = "Mala postura detectada",
                    time = "15:32",
                    color = BadColor,
                    icon = Icons.Default.Close
                )

                TimelineItem(
                    text = "Buena postura",
                    time = "18:00",
                    color = GoodColor,
                    icon = Icons.Default.Check
                )

                TimelineItem(
                    text = "Buena postura",
                    time = "18:00",
                    color = GoodColor,
                    icon = Icons.Default.Check
                )
                TimelineItem(
                    text = "Buena postura",
                    time = "19:00",
                    color = GoodColor,
                    icon = Icons.Default.Check
                )

                TimelineItem(
                    text = "Buena postura",
                    time = "19:30",
                    color = GoodColor,
                    icon = Icons.Default.Check
                )
            }
        }
    }
}
@Composable
fun WeeklyPostureChart(
    data: List<Pair<Float, Float>>, // Pair<BuenaPostura%, MalaPostura%>
    modifier: Modifier = Modifier
) {
    val days = listOf("L", "M", "M", "J", "V", "S", "D")

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        days.forEachIndexed { index, day ->
            val good = data.getOrNull(index)?.first ?: 0f
            val bad = data.getOrNull(index)?.second ?: 0f

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(100.dp * (good + bad) / 100f)
                ) {
                    // Mala postura debajo
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(bad / (good + bad))
                            .background(BadColor)
                            .align(Alignment.BottomCenter)
                    )
                    // Buena postura encima
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(good / (good + bad))
                            .background(GoodColor)
                            .align(Alignment.BottomCenter)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(day, fontSize = 12.sp, color = Color.Black)
            }
        }
    }
}

// ---------------- COMPONENTE DE LA LÍNEA DE TIEMPO ----------------
@Composable
fun TimelineItem(text: String, time: String, color: Color, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {

        // Ícono redondo con borde
        Box(
            modifier = Modifier
                .size(35.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(color, style = Stroke(width = 3.dp.toPx()))
            }
            Icon(icon, contentDescription = null, tint = color)
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(text, fontSize = 15.sp, color = Color.Black)

        Spacer(modifier = Modifier.weight(1f))

        Text(time, color = Color.Gray)
    }
}
