package com.example.semana02_1_martes.Beans

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.semana02_1_martes.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USERNAME TEXT,"
                + "$COLUMN_PASSWORD TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(username: String, password: String, txtBirthday: String, txtPas: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getUserDetails(username: String, password: String): User? {
        val db = this.readableDatabase
        var user: User? = null
        try {
            val cursor = db.query(
                TABLE_USERS,
                arrayOf(COLUMN_USERNAME),
                "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
                arrayOf(username, password),
                null, null, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                user = User(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                    ""
                )
                cursor.close()
            } else {
                Log.d("DatabaseHelper", "No user found with username: $username")
            }
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error fetching user details", e)
        }
        return user
    }
}