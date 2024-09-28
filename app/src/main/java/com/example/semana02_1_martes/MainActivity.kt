package com.example.semana02_1_martes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import android.widget.Toast
import androidx.compose.material.icons.filled.MusicNote
import coil.compose.rememberImagePainter
import com.example.semana02_1_martes.ui.theme.Semana02_1_martesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana02_1_martesTheme {

                var txtUsu by remember { mutableStateOf("") }
                var txtPas by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TopAppBar(
                        title = { Text(text = "MUSIC-TIME") },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.MusicNote,
                                contentDescription = null
                            )
                        }
                    )

                    Image(
                        painter = rememberImagePainter("https://res.cloudinary.com/djia8bsvr/image/upload/v1727479707/egkzzdj50g4l17i1iho4.jpg"),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )

                    Text(
                        text = "Welcome ",
                        fontSize = 32.sp,
                        color = Color.Blue
                    )

                    OutlinedTextField(
                        value = txtUsu,
                        modifier = Modifier.padding(vertical = 15.dp),
                        label = { Text(text = "Ingrese Usuario") },
                        onValueChange = {
                            txtUsu = it
                        }
                    )

                    OutlinedTextField(
                        value = txtPas,
                        modifier = Modifier.padding(vertical = 15.dp),
                        label = { Text(text = "Ingrese Password") },
                        onValueChange = {
                            txtPas = it
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            Color.Blue
                        ),
                        modifier = Modifier
                            .padding(20.dp)
                            .width(250.dp),
                        onClick = {
                            if (txtUsu == "Victor" && txtPas == "Victor123") {
                                val navigate = Intent(this@MainActivity, MainActivity2::class.java)
                                navigate.putExtra("username", txtUsu)  // Pass the username to the next activity
                                startActivity(navigate)
                            } else {
                                Toast.makeText(this@MainActivity, "Usuario y/o password incorrecto", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(text = "Iniciar Sesión", fontSize = 20.sp, color = Color.White)
                    }

                    Text(
                        text = "Si no tienes cuenta, regístrate",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            Color.Blue
                        ),
                        modifier = Modifier
                            .padding(20.dp)
                            .width(250.dp),
                        onClick = {
                            val navigate = Intent(this@MainActivity, RegisterActivity::class.java)
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Regístrate", fontSize = 20.sp, color = Color.White)
                    }
                }
            }
        }
    }
}