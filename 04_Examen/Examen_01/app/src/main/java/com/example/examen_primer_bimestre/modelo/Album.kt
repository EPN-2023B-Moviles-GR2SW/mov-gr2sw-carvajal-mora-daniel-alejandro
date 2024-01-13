package com.example.examen_primer_bimestre.modelo

data class Album(
    val id: Int,
    var nombre: String,
    var artista: String,
    var anioLanzamiento: Int,
    var esExplicito: Boolean,
    var precio: Double,
    var genero: String,
    var canciones: MutableList<Cancion> = mutableListOf()

)