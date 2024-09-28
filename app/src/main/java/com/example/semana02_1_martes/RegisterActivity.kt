package com.example.semana02_1_martes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.semana02_1_martes.Beans.DatabaseHelper
import com.example.semana02_1_martes.ui.theme.Semana02_1_martesTheme
import java.util.*

class RegisterActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dbHelper = DatabaseHelper(this) // Create an instance of DatabaseHelper
        setContent {
            Semana02_1_martesTheme {

                var txtUsu by remember { mutableStateOf("") }
                var txtEmail by remember { mutableStateOf("") }
                var txtBirthday by remember { mutableStateOf("") }
                var txtPas by remember { mutableStateOf("") }
                var txtConfirmPas by remember { mutableStateOf("") }

                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@RegisterActivity,
                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        txtBirthday = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    }, year, month, day
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = rememberImagePainter("https://res.cloudinary.com/djia8bsvr/image/upload/v1727479707/egkzzdj50g4l17i1iho4.jpg"),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp)
                    )

                    Text(
                        text = "REGISTER",
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
                        value = txtEmail,
                        modifier = Modifier.padding(vertical = 15.dp),
                        label = { Text(text = "Ingrese Correo") },
                        onValueChange = {
                            txtEmail = it
                        }
                    )

                    OutlinedTextField(
                        value = txtBirthday,
                        modifier = Modifier
                            .padding(vertical = 15.dp)
                            .clickable { datePickerDialog.show() },
                        label = { Text(text = "Ingrese Cumpleaños") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null
                            )
                        },
                        onValueChange = {
                            txtBirthday = it
                        },
                        readOnly = true
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

                    OutlinedTextField(
                        value = txtConfirmPas,
                        modifier = Modifier.padding(vertical = 15.dp),
                        label = { Text(text = "Confirme Password") },
                        onValueChange = {
                            txtConfirmPas = it
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
                            if (txtUsu.isNotEmpty() && txtEmail.isNotEmpty() && txtBirthday.isNotEmpty() && txtPas.isNotEmpty() && txtPas == txtConfirmPas) {
                                dbHelper.insertUser(txtUsu, txtEmail, txtBirthday, txtPas)
                                Toast.makeText(this@RegisterActivity, "Usuario registrado", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this@RegisterActivity, "Ingrese todos los datos correctamente", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(text = "Registrar", fontSize = 20.sp, color = Color.White)
                    }
                }
            }
        }
    }
}