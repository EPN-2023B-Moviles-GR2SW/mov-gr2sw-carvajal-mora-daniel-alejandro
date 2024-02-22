package com.example.examen_segundo_bimestre.Model

import com.google.firebase.firestore.DocumentSnapshot

data class Album(
    val id: String?,  // Cambiado a String para usar el ID automático de Firestore
    val anioLanzamiento: Int,
    val artista: String,
    val esExplicito: Boolean,
    val genero: String,
    val nombre: String,
    val precio: Double
){
    // Constructor vacío necesario para Firestore
    constructor() : this("", 0, "", false, "", "", 0.0)


    companion object {
        // Método para convertir un DocumentSnapshot a un objeto Album
        fun fromSnapshot(snapshot: DocumentSnapshot): Album {
            val data = snapshot.data!!
            return Album(
                id = snapshot.id,
                anioLanzamiento = (data["anioLanzamiento"] as Long).toInt(),
                artista = data["artista"] as String,
                esExplicito = data["esExplicito"] as Boolean,
                genero = data["genero"] as String,
                nombre = data["nombre"] as String,
                precio = data["precio"] as Double
            )
        }
    }
}