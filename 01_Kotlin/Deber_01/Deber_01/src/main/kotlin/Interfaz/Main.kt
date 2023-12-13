package Interfaz


import Entidades.Album
import Entidades.Cancion
import Operaciones.CrudOperations
import Operaciones.FileWriter
import java.lang.NumberFormatException


// Funcion Principal para Ejecutar las operaciones CRUD
fun main(){

    // Instancia de las Clases de Operaciones
    val fileWriter = FileWriter("albumes_almacenados.txt")
    val crudOperations = CrudOperations(fileWriter)

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
                    print("Nombre del álbum: ")
                    val nombre = readLine() ?: ""
                    print("Año de lanzamiento: ")
                    val anioLanzamiento = readLine()?.toIntOrNull() ?: 0
                    print("Es explícito (true/false): ")
                    val esExplicito = readLine()?.toBoolean() ?: false
                    print("Precio: ")
                    val precio = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Género: ")
                    val genero = readLine() ?: ""

                    crudOperations.crearAlbum(nombre, anioLanzamiento, esExplicito, precio, genero)
                }
                2 -> {
                    println("\n------- Mostrar Álbums Almacenados -------")
                    crudOperations.mostrarAlbum()
                }
                3 -> {
                    println("\n------- Actualizar Álbum -------")
                    print("ID del álbum a actualizar: ")
                    val idActualizar = readLine()?.toIntOrNull() ?: 0
                    crudOperations.actualizarAlbum(idActualizar)
                }
                4 -> {
                    println("\n------- Eliminar Álbum -------")
                    print("ID del álbum a eliminar: ")
                    val idEliminar = readLine()?.toIntOrNull() ?: 0
                    // Implementa la lógica para eliminar el álbum y guarda los cambios en el archivo
                    crudOperations.eliminarAlbum(idEliminar)
                }
                5 -> {
                    println("\n------- Agregar Canción a un Álbum -------")
                    print("ID del álbum al que agregar la canción: ")
                    val idAlbum = readLine()?.toIntOrNull() ?: 0
                    crudOperations.agregarCancionAAlbum(idAlbum)

                }
                6 -> {
                    println("\nSaliendo de la Aplicación....")
                }
                else -> {
                    println("\nOpcion No Válida. Prueba otra vez")
                }
            }
        }catch (ex: NumberFormatException){
            println("Error: Ingresa un Número Valido")
            opcion = 0
        }

    }while(opcion != 6)

}