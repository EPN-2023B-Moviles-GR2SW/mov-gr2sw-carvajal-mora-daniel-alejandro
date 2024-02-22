package com.example.examen_segundo_bimestre.Model

import com.google.firebase.firestore.DocumentSnapshot

data class Cancion(
    val id: String?,  // Cambiado a String para usar el ID automático de Firestore
    var albumId: String,  // Cambiado a String para referenciar el ID del álbum en Firestore
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
) {

    // Constructor vacío necesario para Firestore
    constructor() : this("", "", "", 0.0, "", false, "", "")

    companion object {
        // Método para convertir un DocumentSnapshot a un objeto Cancion
        fun fromSnapshot(snapshot: DocumentSnapshot): Cancion {
            val data = snapshot.data!!
            return Cancion(
                id = snapshot.id,
                albumId = data["albumId"] as String,
                nombre = data["nombre"] as String,
                duracion = data["duracion"] as Double,
                artistaColaborador = data["artistaColaborador"] as String,
                letra = data["letra"] as Boolean,
                escritor = data["escritor"] as String,
                productor = data["productor"] as String
            )
        }
    }
}