package com.tecsup.postulight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.tecsup.postulight.navigation.AppNavigation
import com.tecsup.postulight.ui.theme.PostulightTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val SENSOR_IP = "http://192.168.18.130"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostulightTheme {
                Surface {
                    AppNavigation(SENSOR_IP)
                }
            }
        }
    }
}
