package com.example.examen_segundo_bimestre.Controller

import android.content.ContentValues
import android.content.Context
import com.example.examen_segundo_bimestre.Model.Cancion
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class CancionCRUD {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Cambiar "canciones" por el nombre de tu colección de canciones
    private val cancionesCollection: CollectionReference = db.collection("canciones")



    fun crearCancion(cancion: Cancion): Task<DocumentReference> {
        // Añadir un nuevo documento a la colección de canciones
        return cancionesCollection.add(cancion)
    }

    fun actualizarCancion(cancion: Cancion): Task<Void> {
        // Actualizar un documento existente en la colección de canciones
        val idCancion = cancion.id.toString() // Convertir el ID a String
        return cancionesCollection.document(idCancion).set(cancion)
    }

    fun eliminarCancionPorId(cancionId: String): Task<Void> {
        // Eliminar un documento por su ID de la colección de canciones
        return cancionesCollection.document(cancionId).delete()
    }

    // Obtener todas las canciones (puedes necesitar paginación si hay muchas canciones)
    fun obtenerTodasLasCanciones(): Task<QuerySnapshot> {
        return cancionesCollection.get()
    }

    // Obtener canciones por el ID del álbum
    fun obtenerCancionesPorAlbumId(albumId: String): Task<QuerySnapshot> {
        return cancionesCollection.whereEqualTo("albumId", albumId).get()
    }

    // Obtener una canción por su ID
    fun obtenerCancionPorId(cancionId: String): Task<DocumentSnapshot> {
        return cancionesCollection.document(cancionId).get()
    }

}