package com.tecsup.postulight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tecsup.postulight.arduino.httpGet
import com.tecsup.postulight.navigation.AppNavigation
import com.tecsup.postulight.ui.theme.PostulightTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val IP = "http://192.168.18.130"   // IP del sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppUI(IP)
        }
    }
}

@Composable
fun AppUI(ip: String) {
    var data by remember { mutableStateOf("Sin datos") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                httpGet("$ip/start")
            }
        }) {
            Text("Iniciar Sensor")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                httpGet("$ip/stop")
            }
        }) {
            Text("Detener Sensor")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                val response = httpGet("$ip/data")
                data = response
            }
        }) {
            Text("Obtener Datos")
        }

        Spacer(Modifier.height(30.dp))

        Text("Respuesta del sensor:")
        Text(data)
    }
}
