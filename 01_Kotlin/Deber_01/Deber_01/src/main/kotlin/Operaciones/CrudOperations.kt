package Operaciones

import Entidades.Album
import Entidades.Cancion
import Operaciones.FileWriter

// Clase que contiene las Operaciones CRUD

class CrudOperations(private val fileWriter: FileWriter) {
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
        fileWriter.guardarDatosEnArchivo(albums)
    }

    // Funcion para mostrar la lista de Albumes y sus Canciones (Operacion READ)
    fun mostrarAlbum() {
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
                println("     Compositor: ${cancion.compositor}")
            }
            println()
        }
    }

    // Funcion para mostrar los Albumes disponibles para Actualizar (Operacion READ)
    fun mostrarAlbumAct(){
        albums.forEach{album ->
            println("ID: ${album.id}, Nombre: ${album.nombre}")
        }
    }

    // Funcion para actualizar la informacion del Album (Operacion UPDATE)
    fun actualizarAlbum(id: Int){
        val album = albums.find { it.id == id }
        if (album != null) {
            print("Nuevo nombre del álbum (${album.nombre}): ")
            val nuevoNombre = readLine() ?: album.nombre
            print("Nuevo año de lanzamiento (${album.anioLanzamiento}): ")
            val nuevoAnioLanzamiento = readLine()?.toIntOrNull() ?: album.anioLanzamiento
            print("¿Es explícito? (${album.esExplicito}): ")
            val nuevoEsExplicito = readLine()?.toBoolean() ?: album.esExplicito
            print("Nuevo precio (${album.precio}): ")
            val nuevoPrecio = readLine()?.toDoubleOrNull() ?: album.precio
            print("Nuevo género (${album.genero}): ")
            val nuevoGenero = readLine() ?: album.genero

            // Actualizar los atributos del álbum con los nuevos valores ingresados
            album.nombre = nuevoNombre
            album.anioLanzamiento = nuevoAnioLanzamiento
            album.esExplicito = nuevoEsExplicito
            album.precio = nuevoPrecio
            album.genero = nuevoGenero

            println("Album Actualizado: $album")
            fileWriter.guardarDatosEnArchivo(albums)
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
            fileWriter.guardarDatosEnArchivo(albums)
        }else{
            println("Album con Id $id no ha sido encontrado")
        }
    }

    // Funcion para agregar más canciones al album nusical (Operacion CREATE)
    fun agregarCancionAAlbum(idAlbum: Int){
        val album = albums.find { it.id == idAlbum }

        if (album != null){
            println("\n------- Agregar Canción al Álbum -------")

            val contadorIdLocal = album.canciones.size + 1

            print("Nombre de la canción: ")
            val nombreCancion = readLine() ?: ""
            print("Duración de la canción: ")
            val duracion = readLine()?.toDoubleOrNull() ?: 0.0
            print("Artista de la canción: ")
            val artista = readLine() ?: ""
            print("Año de lanzamiento de la canción: ")
            val anioLanzamientoCancion = readLine()?.toIntOrNull() ?: 0
            print("Compositor de la canción: ")
            val compositor = readLine() ?: ""

            // Atributos que tendra la Cancion Creada
            val nuevaCancion = Cancion(
                nombre = nombreCancion,
                duracion = duracion,
                artista = artista,
                anioLanzamiento = anioLanzamientoCancion,
                compositor = compositor
            )
            album.canciones.add(nuevaCancion)
            println("Cancion Agregada al Album $idAlbum: $nuevaCancion")
            fileWriter.guardarDatosEnArchivo(albums)
        }else{
            println("Album con el Id $idAlbum no ha sido encontrado")
        }
    }

    // Funcion para generar automaticamente un ID único
    private fun generarId(): Int{
        return if(albums.isEmpty()) 1 else albums.maxByOrNull { it.id }!!.id + 1
    }
}