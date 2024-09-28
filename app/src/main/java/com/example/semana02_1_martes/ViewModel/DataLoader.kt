// app/src/main/java/com/example/semana02_1_martes/ViewModel/DataLoader.kt
package com.example.semana02_1_martes.ViewModel

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

data class Musician(
    val id: Int,
    val name: String,
    val manager: String,
    val description: String,
    val available: Boolean,
    val imageUrl: String,
    val socialNetwork: String
)

object DataLoader {
    fun loadMusiciansFromJson(context: Context): List<Musician> {
        val json: String
        try {
            val file = File(context.filesDir, "server/db.json")
            json = file.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listType = object : TypeToken<Map<String, List<Musician>>>() {}.type
        val musiciansMap: Map<String, List<Musician>> = Gson().fromJson(json, listType)
        val musicians = musiciansMap["musician"] ?: emptyList()

        // Log the loaded musicians for debugging
        Log.d("DataLoader", "Loaded musicians: $musicians")

        return musicians
    }
}