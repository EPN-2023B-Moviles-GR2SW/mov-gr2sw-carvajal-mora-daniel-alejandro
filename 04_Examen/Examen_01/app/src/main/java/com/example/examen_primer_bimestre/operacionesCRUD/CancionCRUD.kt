package com.example.examen_primer_bimestre.operacionesCRUD

import com.example.examen_primer_bimestre.BaseDeDatos
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion

class CancionCRUD {

    // Operaciones CRUD para trabajar con Cancion

    // Operacion CREATE para crear una nueva Cancion
    fun create(id: Int, cancion: Cancion) {
        val listaCanciones = getAllCanciones()
        // Verificar que el ID no exista ya en la lista
        if (listaCanciones.any { it.id == id }) {
            throw IllegalArgumentException("Ya existe una canción con el ID $id.")
        }
        // Asignar el ID proporcionado a la canción
        cancion.id = id
        // Agregar la canción a la lista
        listaCanciones.add(cancion)
    }

    // Operacion READ para visualizar todas las canciones almacenadas
    fun getAllCanciones(): ArrayList<Cancion> {
        return BaseDeDatos.bibliotecaCanciones
    }

    // Operacion READ para visualizar una Canción por su ID
    fun getById(id: Int): Cancion? {
        var cancionEncontrada: Cancion? = null
        getAllCanciones().forEach { cancion: Cancion ->
            if (cancion.id == id) cancionEncontrada = cancion
        }
        return cancionEncontrada
    }

    // Operacion UPDATE para actualizar una Canción
    fun updateCancion(cancionActualizada: Cancion) {
        val listaCanciones = getAllCanciones()
        listaCanciones.forEachIndexed { index, cancion ->
            if (cancion.id == cancionActualizada.id) {
                listaCanciones[index] = cancionActualizada
                return
            }
        }
    }

    // Función auxiliar para obtener todos los álbumes
    private fun getAllAlbumes(): MutableList<Album> {
        return BaseDeDatos.bibliotecaAlbumes
    }

    // Operacion DELETE para eliminar una Cancion por su ID
    fun eliminarCancionById(id: Int) {
        val listaCanciones = getAllCanciones()
        val cancion = getById(id)

        if (cancion != null) {
            // Eliminar la canción de la lista
            listaCanciones.remove(cancion)

            // Eliminar la referencia de la canción en los álbumes
            val listaDeAlbumes = getAllAlbumes()
            listaDeAlbumes.forEach { album ->
                album.canciones.remove(cancion)
            }
        }
    }


}