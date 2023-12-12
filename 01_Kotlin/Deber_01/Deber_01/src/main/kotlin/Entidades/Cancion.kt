package Entidades

// Entidad de Canciones que contiene un Álbum Musical con sus Propiedades

data class Cancion(
    val id: Int,
    var nombre: String,
    var duracion: Double,
    var artista: String,
    var anioLanzamiento: Int,
    var compositor: String,
)
