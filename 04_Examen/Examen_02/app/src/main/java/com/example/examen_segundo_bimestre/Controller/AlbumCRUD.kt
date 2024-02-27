package com.example.examen_segundo_bimestre.Controller

import android.content.ContentValues
import android.content.Context
import com.example.examen_segundo_bimestre.Model.Album
import com.example.examen_segundo_bimestre.Model.Cancion
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AlbumCRUD {

    private val db = FirebaseFirestore.getInstance()


    companion object {
        fun crearAlbumFromDocument(document: DocumentSnapshot): Album {
            val id = document.id
            val nombre = document.data?.get("nombre") as String?
            val artista = document.data?.get("artista") as String?
            val anioLanzamiento = document.data?.get("anioLanzamiento") as Long?
            val esExplicito = document.data?.get("esExplicito") as Boolean?
            val precio = document.data?.get("precio") as Double?
            val genero = document.data?.get("genero") as String?

            if (id == null || nombre == null || artista == null || anioLanzamiento == null || esExplicito == null || precio == null || genero == null) {
                return Album()
            }

            return Album(id, nombre, artista, anioLanzamiento.toInt(), esExplicito, precio, genero)
        }
    }

    fun obtenerTodosAlbumes(): Task<QuerySnapshot> {
        return db.collection("albums").get()
    }

    fun obtenerUnAlbum(id: String): Task<DocumentSnapshot> {
        return db.collection("albums").document(id).get()
    }


    fun crearAlbum(album: Album) {
        val albumData = hashMapOf(
            "nombre" to album.nombre,
            "artista" to album.artista,
            "anioLanzamiento" to album.anioLanzamiento,
            "esExplicito" to album.esExplicito,
            "precio" to album.precio,
            "genero" to album.genero
        )

        db.collection("albums").add(albumData)
            .addOnSuccessListener { documentReference ->
                // Asignar el ID asignado por Firestore al álbum
                album.id = documentReference.id
            }
    }

    fun updateAlbum(id: String, album: Album) {
        val albumData = hashMapOf(
            "nombre" to album.nombre,
            "artista" to album.artista,
            "anioLanzamiento" to album.anioLanzamiento,
            "esExplicito" to album.esExplicito,
            "precio" to album.precio,
            "genero" to album.genero
        )

        db.collection("albums").document(id).set(albumData)
    }

    fun removeAlbum(id: String) {
        db.collection("albums").document(id).delete()
    }

    // Función para obtener todas las canciones de un álbum desde Firestore
    fun obtenerCancionesPorAlbumId(albumId: String): Task<QuerySnapshot> {
        return db.collection("canciones").whereEqualTo("albumId", albumId).get()
    }

    fun obtenerAlbumPorId(context: Context, albumId: Int, onAlbumObtenido: (Album?) -> Unit) {
        val albumDocument = db.collection("albums").document(albumId.toString())

        albumDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                val album = if (documentSnapshot.exists()) {
                    AlbumCRUD.crearAlbumFromDocument(documentSnapshot)
                } else {
                    null
                }

                onAlbumObtenido(album)
            }
            .addOnFailureListener { e ->
                // Manejar el fallo, por ejemplo, mostrar un mensaje al usuario
                onAlbumObtenido(null)
            }
    }
}