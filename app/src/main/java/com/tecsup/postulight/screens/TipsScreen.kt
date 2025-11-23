package com.tecsup.postulight.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.postulight.R

data class Tip(val title: String, val imageRes: Int)

@Composable
fun TipsScreen() {
    val tips = listOf(
        Tip("Mantén tu espalda recta", R.drawable.tip1),
        Tip("Haz pausas activas", R.drawable.tip2),
        Tip("Ajusta tu silla y monitor", R.drawable.tip3),
        Tip("Evita encorvar los hombros", R.drawable.tip4),
        Tip("Bebe suficiente agua", R.drawable.tip5),
        Tip("Estira cuello y hombros", R.drawable.tip6)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Consejos para tu postura",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,   // centra el contenido
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color(0xFF13485E),
            modifier = Modifier
                .fillMaxWidth()             // 🔥 NECESARIO para centrar en la pantalla
                .padding(bottom = 16.dp)
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(tips) { tip ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Imagen ajustada
                        Image(
                            painter = painterResource(id = tip.imageRes),
                            contentDescription = tip.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                            contentScale = ContentScale.Crop   // Ajusta la imagen sin deformarse
                        )

                        // Fondo gris + texto centrado
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(Color(0xFFE0E0E0)),
                            contentAlignment = Alignment.Center    // 🔥 CENTRA EL TEXTO AL 100%
                        ) {
                            Text(
                                text = tip.title,
                                fontSize = 16.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                                color = Color.Black,
                                maxLines = 2,
                                textAlign = TextAlign.Center,       // 🔥 CENTRA EL TEXTO HORIZONTAL
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                    }
                }
            }
        }
    }
}
