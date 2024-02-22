package com.example.examen_segundo_bimestre.Controller

import android.content.ContentValues
import android.content.Context
import com.example.examen_segundo_bimestre.Database.BaseDeDatosHelper
import com.example.examen_segundo_bimestre.Model.Album
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.DocumentSnapshot

class AlbumCRUD(context: Context) {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val albumsCollection: CollectionReference = db.collection("albums")

    fun crearAlbum(album: Album): Task<DocumentReference> {
        return albumsCollection.add(album)
    }

    fun obtenerAlbums(): Task<QuerySnapshot> {
        return albumsCollection.get()
    }

    fun obtenerAlbumPorId(albumId: String): Task<DocumentSnapshot> {
        return albumsCollection.document(albumId).get()
    }

    fun actualizarAlbum(album: Album): Task<Void> {
        // Actualizar un documento existente en la colección de álbumes
        val idAlbum = album.id ?: ""
        return albumsCollection.document(idAlbum).set(album)
    }

    fun eliminarAlbumPorId(albumId: String) {
        albumsCollection.document(albumId).delete()
    }
}