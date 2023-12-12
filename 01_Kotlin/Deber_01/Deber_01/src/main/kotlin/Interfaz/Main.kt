package Interfaz
import Entidades.Album
import Entidades.Cancion
import java.lang.NumberFormatException

// Clase principal que contiene las Operaciones CRUD

class CrudMusicalApp{
    private val albums = mutableListOf<Album>()

    // Funcion para Guardar Datos creados en un Archivo


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

    var opcion: Int

    do{
        println("\nMenu:")
        println("1. Crear Álbum")
        println("2. Mostrar Álbums")
        println("3. Actualizar Álbum")
        println("4. Eliminar Álbum")
        println("5. Agregar Canción a Álbum")
        println("6. Salir")
        print("Seleccione una opción: ")

        try{
            opcion = readLine()?.toInt() ?:0

            // Ciclo Switch en Kotlin
            when (opcion){
                1 -> {
                    println("\n------- Crear Álbum -------")
                }
                2 -> {
                    println("\n------- Mostrar Álbums Almacenados -------")
                }
                3 -> {
                    println("\n------- Actualizar Álbum -------")
                }
                4 -> {
                    println("\n------- Eliminar Álbum -------")
                }
                5 -> {
                    println("\n------- Agregar Canción a un Álbum -------")
                }
                6 -> {
                    println("\nSaliendo de la Aplicacion....")
                }
                else -> {
                    println("\nOpcion No Válida. Prueba otra vez")
                }
            }

        }catch (ex: NumberFormatException){
            println("Error: Ingresa un Número Valido")
            opcion = 0
        }

    }while (opcion != 6)

}