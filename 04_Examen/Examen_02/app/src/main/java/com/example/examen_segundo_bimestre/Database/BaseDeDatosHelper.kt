package com.example.examen_segundo_bimestre.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class BaseDeDatosHelper(context: Context) {

    // Referencia a la colección "albums" en Firestore
    val albumsCollection: CollectionReference = FirebaseFirestore.getInstance().collection("albums")

    // Referencia a la colección "canciones" en Firestore
    val cancionesCollection: CollectionReference = FirebaseFirestore.getInstance().collection("canciones")

    // Constantes para el nombre de las colecciones
    companion object {
        const val COLECCION_ALBUMS = "albums"
        const val COLECCION_CANCIONES = "canciones"
    }
}