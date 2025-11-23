package com.tecsup.postulight.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.postulight.R
import kotlinx.coroutines.delay
import com.tecsup.postulight.navigation.BottomNavItem


@Composable
fun HomeScreen(ip: String, navController: NavController) {

    var sensorActivo by remember { mutableStateOf(false) }
    var postura by remember { mutableStateOf("BUENA") }

    // Simulación: aquí luego llamamos al ESP8266
    LaunchedEffect(sensorActivo) {
        while (sensorActivo) {
            delay(1500)
        }
    }

    val imagenPostura = when (postura) {
        "BUENA" -> R.drawable.postura_buena
        "MALA" -> R.drawable.postura_mala
        else -> R.drawable.postura_buena
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ⭐ FONDO BLANCO EN TODA LA PANTALLA

            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {


        // --------------------- ESTADO ACTUAL ---------------------
        Text(
            "ESTADO ACTUAL",
            fontWeight = FontWeight.Bold,
            color = Color(0xff13485e),
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sensor y Lampara")

            Switch(
                checked = sensorActivo,
                onCheckedChange = { sensorActivo = it }
            )
        }

        OutlinedButton(
            onClick = { sensorActivo = !sensorActivo },
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(if (sensorActivo) "Sensor Activado" else "Vincular Sensor y Lámpara")
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ------------------ TARJETA DE POSTURA ------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Tarjeta izquierda
            Column(
                modifier = Modifier
                    .weight(0.55f)
                    .background(Color(0xFFE4F2F6), RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Estado actual de la Postura",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = imagenPostura),
                    contentDescription = "Estado postura",
                    modifier = Modifier.size(110.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(horizontal = 18.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (postura == "BUENA") "Correcta" else "Incorrecta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Tarjetas de tiempo
            Column(
                modifier = Modifier.weight(0.45f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = CardDefaults.cardColors(Color(0xFFD9EEF5)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Tiempo en postura correcta", fontSize = 12.sp)
                        Text("3 h 45 m", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = CardDefaults.cardColors(Color(0xFFD9EEF5)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Tiempo en incorrecta postura", fontSize = 12.sp)
                        Text("1 h 30 m", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ------------------ SOBRE LA CUENTA ------------------

        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White) // ⭐ FONDO BLANCO
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Sobre la Cuenta", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(12.dp))


                CuentaItem("Información personal") {
                    navController.navigate("personal_info")
                }

                CuentaItem(
                    texto = "Consejos",
                    icono = { Icon(Icons.Default.Lightbulb, contentDescription = "Consejos") }
                ) {
                    navController.navigate("tips")
                }


            }

        }

        Spacer(modifier = Modifier.height(20.dp))

// ------------------ NOTIFICACIONES ------------------

        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White) // ⭐ FONDO BLANCO
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Notificaciones", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(12.dp))

                NotiItem("30 min con buena postura")
                NotiItem("Mantuviste una postura correcta durante 1 hora")
                NotiItem("5 min en mala postura")
                NotiItem("Postura incorrecta detectada")
                NotiItem("Sensor y lámpara vinculados correctamente")
                NotiItem("Sensor desconectado")
                NotiItem("Tu postura fue correcta el 75% del día")
                NotiItem("Nuevo récord: 2 horas seguidas con buena postura")
                NotiItem("Recuerda mantener la espalda recta")
                NotiItem("¿Necesitas una pausa? Llevas 45 min sentado")
            }
        }

    }
}

@Composable
fun CuentaItem(
    texto: String,
    icono: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icono != null) {
                icono()
                Spacer(modifier = Modifier.width(10.dp))
            } else {
                Icon(Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(texto)
        }

        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
}


@Composable
fun NotiItem(texto: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Notifications, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto)
    }
}
