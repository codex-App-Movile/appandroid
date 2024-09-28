package com.example.semana02_1_martes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.semana02_1_martes.ui.theme.Semana02_1_martesTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote

class MainActivity2 : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana02_1_martesTheme {

                val username = intent.getStringExtra("username")  // Recibe el nombre del usuario

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TopAppBar(
                        title = { Text(text = "") },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.MusicNote,
                                contentDescription = null
                            )
                        }
                    )

                    Text(
                        text = "BIENVENIDO, $username",  // Muestra el nombre del usuario
                        fontSize = 32.sp,
                        color = Color.Blue
                    )

                    MusicianList()  // Display the list of musician cards
                }
            }
        }
    }
}

@Composable
fun MusicianCard(name: String, manager: String, description: String, imageUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = name, fontSize = 20.sp, color = Color.Black)
            Text(text = "Manager: $manager", fontSize = 16.sp, color = Color.Gray)
            Text(text = description, fontSize = 14.sp, color = Color.DarkGray)
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Handle view profile click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "View Profile", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicianList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),  // Enable vertical scrolling
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(text = "Musicos") }
        )

        val musicians = listOf(
            Musician(
                name = "Grupo 5",
                manager = "David Orozco",
                description = "Grupo 5 es una banda peruana de cumbia y merengue fundada el 31 de enero de 1973 en la ciudad de Monsefú. Sus líderes, actualmente, son los hermanos: Elmer, Andy y Christian, hijos de Elmer Yaipén Uypan, fundador junto a su hermano Víctor.",
                available = true,
                imageUrl = "https://res.cloudinary.com/djia8bsvr/image/upload/v1714744341/grupo-5_ni44dp.jpg"
            ),
            Musician(
                name = "Agua Marina",
                manager = "Manuel Quiroga Querevalú",
                description = "Agua Marina es una banda peruana de cumbia fundada el 30 de agosto de 1976 en la ciudad de Sechura, en el departamento de Piura.​Es considerada como la trilogía de la cumbia, junto con Armonía 10 y el Grupo 5, ​ pieza importante en el afianzamiento de la cumbia peruana como género musical.",
                available = false,
                imageUrl = "https://res.cloudinary.com/djia8bsvr/image/upload/v1714744341/agua_marina_xeaqnf.png"
            ),
            Musician(
                name = "Eddy Herrera",
                manager = "Eddy Herrera",
                description = "Eduardo José Herrera de los Ríos, más conocido como Eddy Herrera, es un cantante dominicano de merengue. Perteneció como uno de los grandes vocalistas del conjunto musical liderado por Wilfrido Vargas en la década de 1980.",
                available = false,
                imageUrl = "https://res.cloudinary.com/djia8bsvr/image/upload/v1714744341/eddy_herrera_h309en.jpg"
            ),
            Musician(
                name = "Armonía 10",
                manager = "Walter Lozada",
                description = "Armonía 10 es una banda peruana de cumbia fundada en 1972 en Piura. Es conocida por su estilo único y sus éxitos que han marcado la historia de la cumbia peruana.",
                available = true,
                imageUrl = "https://portal.andina.pe/EDPfotografia3/Thumbnail/2020/12/03/000730609W.jpg"
            ),
            Musician(
                name = "Los Mirlos",
                manager = "Jorge Rodríguez",
                description = "Los Mirlos es una banda peruana de cumbia amazónica fundada en 1973 en Moyobamba. Son conocidos por su estilo que mezcla la cumbia con ritmos amazónicos.",
                available = true,
                imageUrl = "https://e.rpp-noticias.io/xlarge/2024/02/28/054605_1545299.webp"
            )
        )

        musicians.forEach { musician ->
            MusicianCard(
                name = musician.name,
                manager = musician.manager,
                description = musician.description,
                imageUrl = musician.imageUrl
            )
        }
    }
}

data class Musician(
    val name: String,
    val manager: String,
    val description: String,
    val available: Boolean,
    val imageUrl: String
)