import java.io.File
import java.io.FileWriter

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

// Entidad de Canciones que contiene un Álbum Musical con sus Propiedades

data class Cancion(
    val id: Int,
    var nombre: String,
    var duracion: Double,
    var artista: String,
    var anioLanzamiento: Int,
    var compositor: String,
)

// Clase principal que contiene las Operaciones CRUD

class CrudMusicalApp{
    private val albums = mutableListOf<Album>()

    // Funcion para crear un nuevo Album (Operacion CREATE)
    fun crearAlbum(nombre: String, anioLanzamiento: Int, esExplicito: Boolean, precio: Double, genero: String){
        val nuevoAlbum = Album(
            generarId(),
            nombre,
            anioLanzamiento,
            esExplicito,
            precio,
            genero
        )
        albums.add(nuevoAlbum)
        println("Album Agregado: $nuevoAlbum")
    }

    // Funcion para mostrar la lista de Albumes y sus Canciones (Operacion READ)
    fun mostrarAlbum() {
        println("Lista de Albums:")
        albums.forEach { album ->
            println("ID: ${album.id}")
            println("Nombre: ${album.nombre}")
            println("Año de Lanzamiento: ${album.anioLanzamiento}")
            println("Es Explicito: ${album.esExplicito}")
            println("Precio: ${album.precio}")
            println("Genero: ${album.genero}")
            println("Canciones:")
            album.canciones.forEach { cancion ->
                println("   - ID: ${cancion.id}")
                println("     Nombre: ${cancion.nombre}")
                println("     Duración: ${cancion.duracion}")
                println("     Artista: ${cancion.artista}")
                println("     Año de Lanzamiento: ${cancion.anioLanzamiento}")
            }
            println()
        }
    }

    // Funcion para actualizar la informacion del Album (Operacion UPDATE)
    fun actualizarAlbum(id: Int, nombre: String, anioLanzamiento: Int, esExplicito: Boolean, precio: Double, genero: String){
        val album = albums.find { it.id == id }
        if (album != null){
            album.nombre = nombre
            album.anioLanzamiento = anioLanzamiento
            album.esExplicito = esExplicito
            album.precio = precio
            album.genero = genero
            println("Album Actualizado: $album")
        }else{
            println("Album con el Id $id no ha sido encontrado")
        }
    }

    // Funcion para eliminar un album por su ID (Operacion DELETE)
    fun eliminarAlbum(id: Int){
        val album = albums.find { it.id == id}
        if (album != null){
            albums.remove(album)
            println("Album Eliminado: $album")
        }else{
            println("Album con Id $id no ha sido encontrado")
        }
    }

    // Funcion para agregar más canciones al album nusical (Operacion CREATE)
    fun agregarCancionAAlbum(idAlbum: Int, nombre: String, duracion: Double, artista: String, anioLanzamiento: Int, compositor: String){
        val album = albums.find { it.id == idAlbum }
        if (album != null){
            val nuevaCancion = Cancion(
                generarId(),
                nombre,
                duracion,
                artista,
                anioLanzamiento,
                compositor
            )
            album.canciones.add(nuevaCancion)
            println("Cancion Agregada al Album $idAlbum: $nuevaCancion")
        }else{
            println("Album con el Id $idAlbum no ha sido encontrado")
        }
    }

    // Funcion para generar automaticamente un ID único
    private fun generarId(): Int{
        return if(albums.isEmpty()) 1 else albums.maxByOrNull { it.id }!!.id + 1
    }
}


// Funcion Principal para Ejecutar las operaciones CRUD
fun main(){
    // Crear una Instancia de la aplicacion CRUD musical
    val crudMusicalApp = CrudMusicalApp()

    // Crear álbum
    crudMusicalApp.crearAlbum("The Getaway", 2016, true, 29.99, "Funk Rock")
    crudMusicalApp.crearAlbum("Black Album", 1991, false, 15.00, "Thrash Metal")
    crudMusicalApp.mostrarAlbum()

    /*
    // Actualizar álbum
    crudMusicalApp.actualizarAlbum(1, "AlbumActualizado", 2023, false, 15.99, "Funk")
    crudMusicalApp.mostrarAlbum()

    // Eliminar álbum
    crudMusicalApp.eliminarAlbum(1)
    crudMusicalApp.mostrarAlbum()

     */
    // Agregar canción a álbum
    crudMusicalApp.agregarCancionAAlbum(1, "Dark Necessities", 4.5, "Red Hot Chili Peppers", 2016, "Anthony Kiedis")
    crudMusicalApp.agregarCancionAAlbum(1, "Go Robot", 2.5, "Red Hot Chili Peppers", 2016, "Rick Rubin")
    crudMusicalApp.mostrarAlbum()

}