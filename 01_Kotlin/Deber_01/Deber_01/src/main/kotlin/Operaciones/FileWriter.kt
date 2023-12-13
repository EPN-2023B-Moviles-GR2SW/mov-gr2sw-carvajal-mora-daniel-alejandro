package Operaciones

import Entidades.Album
import Entidades.Cancion
import java.io.File
import java.io.FileWriter
import java.io.FileReader
import java.io.BufferedReader

class FileWriter(private val archivo: String) {

    fun guardarDatosEnArchivo(albums: List<Album>) {
        try {
            val archivo = File(archivo)
            FileWriter(archivo).use { fw ->
                fw.write("---- Álbumes Almacenados ----\n") // Título del Archivo
                albums.forEach { album ->
                    with(album) {
                        fw.write("ID: $id\n")
                        fw.write("Nombre: $nombre\n")
                        fw.write("Año de Lanzamiento: $anioLanzamiento\n")
                        fw.write("Es Explícito: $esExplicito\n")
                        fw.write("Precio: $precio\n")
                        fw.write("Género: $genero\n")

                        // Si hay canciones se escriben en el archivo
                        if (canciones.isNotEmpty()) {
                            fw.write("---- Canciones del Álbum ----\n") // Título del Archivo
                            canciones.forEach { cancion ->
                                with(cancion) {
                                    fw.write("  - ID: $id\n")
                                    fw.write("    Nombre: $nombre\n")
                                    fw.write("    Duración: $duracion\n")
                                    fw.write("    Artista: $artista\n")
                                    fw.write("    Año de Lanzamiento: $anioLanzamiento\n")
                                    fw.write("    Compositor: $compositor\n")
                                }
                            }
                        }
                        fw.write("\n")
                    }
                }
            }
            println("Datos Guardados en el Archivo.")
        } catch (ex: Exception) {
            println("Error al Guardar los Datos en el Archivo")
        }
    }

}

