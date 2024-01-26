package com.example.examen_con_sqlite.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen_con_sqlite.Model.Album

class BaseDeDatosHelperAlbum(context: Context):
    SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_NOMBRE = "biblioteca_musical"
        const val DATABASE_VERSION = 1
        const val TABLA_ALBUM = "Album"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // Crear Scripts de SQL para una Base de Datos

        // Creacion de la tabla Album

        val scriptSQLCrearTablaAlbum =
            """
                CREATE TABLE $TABLA_ALBUM( 
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT,
                    artista TEXT,
                    anioLanzamiento INTEGER,
                    esExplicito BIT,
                    precio REAL,
                    genero TEXT
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaAlbum)



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}