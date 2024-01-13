package com.example.examen_primer_bimestre.modelo

data class Album(
    var id: Int,
    var nombre: String,
    var artista: String,
    var anioLanzamiento: Int,
    var esExplicito: Boolean = false,
    var precio: Double,
    var genero: String,
    var canciones: MutableList<Cancion> = mutableListOf()

)