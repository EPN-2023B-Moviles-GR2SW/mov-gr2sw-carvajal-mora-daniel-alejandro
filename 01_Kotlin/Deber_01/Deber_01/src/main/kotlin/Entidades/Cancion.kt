package Entidades

// Entidad de Canciones que contiene un Álbum Musical con sus Atributos

data class Cancion(
    val id: Int = generarId(),
    var nombre: String,
    var duracion: Double,
    var artista: String,
    var anioLanzamiento: Int,
    var compositor: String,
){
    companion object{
        private var contadorId: Int = 1

        private fun generarId(): Int{
            return contadorId++
        }
    }
}
