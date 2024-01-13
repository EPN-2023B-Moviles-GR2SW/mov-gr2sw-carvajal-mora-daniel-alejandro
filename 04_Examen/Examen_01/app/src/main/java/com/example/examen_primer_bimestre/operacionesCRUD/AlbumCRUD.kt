package com.example.examen_primer_bimestre.operacionesCRUD

import com.example.examen_primer_bimestre.BaseDeDatos
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion


class AlbumCRUD {

    // Operaciones CRUD para trabajar con Album

    // Operacion CREATE para crear un nuevo album
    fun crearAlbum(album: Album) {
        val listaAlbumes = getAllAlbums()
        if (listaAlbumes.isEmpty()) {
            album.id = 0
        } else {
            album.id = listaAlbumes.last().id + 1
        }
        listaAlbumes.add(album)
    }

    // Operacion READ para visualizar todos los albumes almacenados
    fun getAllAlbums(): ArrayList<Album> {
        return BaseDeDatos.bibliotecaAlbumes
    }

    // Operacion READ para visualizar un Album por su ID
    fun getById(id: Int): Album? {
        var albumEncontrado: Album? = null
        getAllAlbums().forEach { album: Album ->
            if (album.id == id) albumEncontrado = album
        }
        return albumEncontrado
    }

    // Operacion UPDATE para actualizar un Album
    fun updateAlbum(albumActualizado: Album) {
        val listaAlbumes = getAllAlbums()
        listaAlbumes.forEachIndexed { index, album ->
            if (album.id == albumActualizado.id) {
                listaAlbumes[index] = albumActualizado
                return
            }
        }
    }

    // Función auxiliar para obtener todas las canciones
    private fun getAllCanciones(): MutableList<Cancion> {
        return BaseDeDatos.bibliotecaCanciones
    }

    // Operacion DELETE para eliminar un Album por su ID
    fun eliminarAlbumById(id: Int){
        val listaAlbumes = getAllAlbums()
        val album = getById(id)

        if (album != null) {
            // Eliminar la referencia del álbum en las canciones
            val listaCanciones = getAllCanciones()
            album.canciones.forEach { cancion ->
                listaCanciones.remove(cancion)
            }

            // Eliminar el álbum de la lista
            listaAlbumes.remove(album)
        }
    }

}