package com.tecsup.postulight.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Color principal personalizado ⭐
private val PrimaryColor = Color(0xFF3BA6BD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    var notificationsEnabled by remember { mutableStateOf(true) }
    var themeOption by remember { mutableStateOf("Sistema") }
    var language by remember { mutableStateOf("Español") }
    var batterySaver by remember { mutableStateOf(false) }
    var locationEnabled by remember { mutableStateOf(false) }

    val backgroundColor = Color.White

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Ajustes", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            /** -----------------------------------------------------
             *   NOTIFICACIONES
             *  ----------------------------------------------------- */
            ListItem(
                headlineContent = { Text("Notificaciones", color = Color.Black) },
                supportingContent = { Text("Activar o desactivar notificaciones", color = Color.Gray) },
                leadingContent = {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = PrimaryColor)
                },
                trailingContent = {
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = PrimaryColor,
                            checkedTrackColor = PrimaryColor.copy(alpha = 0.3f)
                        )
                    )
                }
            )
            Divider(color = PrimaryColor.copy(alpha = 0.2f))


            /** -----------------------------------------------------
             *   TEMA DE LA APP
             *  ----------------------------------------------------- */
            Text(
                "Tema de la aplicación",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                AssistChip(
                    onClick = { themeOption = "Claro" },
                    label = { Text("Claro", color = Color.Black) },
                    leadingIcon = {
                        Icon(Icons.Default.LightMode, null, tint = PrimaryColor)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = PrimaryColor.copy(alpha = 0.15f)
                    )
                )

                AssistChip(
                    onClick = { themeOption = "Oscuro" },
                    label = { Text("Oscuro", color = Color.Black) },
                    leadingIcon = {
                        Icon(Icons.Default.DarkMode, null, tint = PrimaryColor)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = PrimaryColor.copy(alpha = 0.15f)
                    )
                )

                AssistChip(
                    onClick = { themeOption = "Sistema" },
                    label = { Text("Sistema", color = Color.Black) },
                    leadingIcon = {
                        Icon(Icons.Default.Brightness6, null, tint = PrimaryColor)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = PrimaryColor.copy(alpha = 0.15f)
                    )
                )
            }

            Text(
                "Seleccionado: $themeOption",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.DarkGray
            )

            Divider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = PrimaryColor.copy(alpha = 0.2f)
            )


            /** -----------------------------------------------------
             *   IDIOMA
             *  ----------------------------------------------------- */
            ListItem(
                headlineContent = { Text("Idioma", color = Color.Black) },
                supportingContent = { Text("Idioma actual: $language", color = Color.Gray) },
                leadingContent = { Icon(Icons.Default.Language, contentDescription = null, tint = PrimaryColor) },
                trailingContent = { Text(language, color = Color.DarkGray) }
            )
            Divider(color = PrimaryColor.copy(alpha = 0.2f))


            /** -----------------------------------------------------
             *   AHORRO DE ENERGÍA
             *  ----------------------------------------------------- */
            ListItem(
                headlineContent = { Text("Ahorro de energía", color = Color.Black) },
                supportingContent = { Text("Reduce animaciones y uso de CPU", color = Color.Gray) },
                leadingContent = { Icon(Icons.Default.Bolt, contentDescription = null, tint = PrimaryColor) },
                trailingContent = {
                    Switch(
                        checked = batterySaver,
                        onCheckedChange = { batterySaver = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = PrimaryColor,
                            checkedTrackColor = PrimaryColor.copy(alpha = 0.3f)
                        )
                    )
                }
            )
            Divider(color = PrimaryColor.copy(alpha = 0.2f))


            /** -----------------------------------------------------
             *   UBICACIÓN
             *  ----------------------------------------------------- */
            ListItem(
                headlineContent = { Text("Ubicación", color = Color.Black) },
                supportingContent = { Text("Acceso a GPS para funciones precisas", color = Color.Gray) },
                leadingContent = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = PrimaryColor) },
                trailingContent = {
                    Switch(
                        checked = locationEnabled,
                        onCheckedChange = { locationEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = PrimaryColor,
                            checkedTrackColor = PrimaryColor.copy(alpha = 0.3f)
                        )
                    )
                }
            )
            Divider(color = PrimaryColor.copy(alpha = 0.2f))


            /** -----------------------------------------------------
             *   CAMBIAR CONTRASEÑA
             *  ----------------------------------------------------- */
            ListItem(
                headlineContent = { Text("Cambiar contraseña", color = Color.Black) },
                leadingContent = { Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryColor) },
                trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                modifier = Modifier.clickable {
                    // navegar a otra pantalla
                }
            )

            Divider(color = PrimaryColor.copy(alpha = 0.2f))


            /** -----------------------------------------------------
             *   INFORMACIÓN
             *  ----------------------------------------------------- */
            Text(
                "Información",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            ListItem(
                headlineContent = { Text("Versión de la app", color = Color.Black) },
                supportingContent = { Text("1.0.0", color = Color.Gray) },
                leadingContent = { Icon(Icons.Default.Info, contentDescription = null, tint = PrimaryColor) }
            )

            ListItem(
                headlineContent = { Text("Política de privacidad", color = Color.Black) },
                leadingContent = { Icon(Icons.Default.Description, contentDescription = null, tint = PrimaryColor) },
                modifier = Modifier.clickable { }
            )

            /** -----------------------------------------------------
             *   RESET APP
             *  ----------------------------------------------------- */
            Button(
                onClick = { /* reiniciar app */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text("Restablecer aplicación", color = Color.White)
            }
        }
    }
}
