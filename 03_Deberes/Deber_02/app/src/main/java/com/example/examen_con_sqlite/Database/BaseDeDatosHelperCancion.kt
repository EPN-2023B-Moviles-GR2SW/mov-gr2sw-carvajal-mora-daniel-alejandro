package com.example.examen_con_sqlite.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen_con_sqlite.Model.Album
import com.example.examen_con_sqlite.Model.Cancion

class BaseDeDatosHelperCancion(context: Context): SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_NOMBRE = "biblioteca_musical"
        const val DATABASE_VERSION = 1
        const val TABLA_CANCION = "Cancion"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // Crear Scripts de SQL para una Base de Datos

        // Creacion de la tabla Cancion

        val scriptSQLCrearTablaCancion =
            """
                CREATE TABLE $TABLA_CANCION( 
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    albumId INTEGER,
                    nombre TEXT,
                    duracion REAL,
                    artistaColaborador TEXT,
                    letra BIT,
                    escritor TEXT,
                    productor TEXT,
                    FOREIGN KEY(albumId) REFERENCES Album(id)
                    
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaCancion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    // Funcion para Crear una Cancion con la Base de Datos


}