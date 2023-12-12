package Entidades

// Entidad de Album Musical con sus Propiedades

data class Album(
    val id: Int,
    var nombre: String,
    var anioLanzamiento: Int,
    var esExplicito: Boolean,
    var precio: Double,
    var genero: String,
    var canciones: MutableList<Cancion> = mutableListOf()

)