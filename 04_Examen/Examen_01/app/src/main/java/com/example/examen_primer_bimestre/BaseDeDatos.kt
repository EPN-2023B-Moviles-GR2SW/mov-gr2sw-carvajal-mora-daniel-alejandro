package com.example.examen_primer_bimestre

import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion

class BaseDeDatos {

    companion object{

        // Se crean Arreglos para almacenar la Biblioteca Musical
        var bibliotecaAlbumes: ArrayList<Album> = arrayListOf()
        var bibliotecaCanciones: ArrayList<Cancion> = arrayListOf()

        init {

            // Inserción de Datos para Inicio de Aplicación en Album
            bibliotecaAlbumes.add(
                Album(
                    1,
                    "Californication",
                    "Red Hot Chili Peppers",
                    1999,
                    false,
                    19.99,
                    "Funk Rock"
                )
            )

            bibliotecaAlbumes.add(
                Album(
                    2,
                    "In Utero",
                    "Nirvana",
                    1993,
                    true,
                    25.99,
                    "Grunge"

                )
            )

            // Inserción de Datos para Inicio de Aplicación en Cancion

            bibliotecaCanciones.add(
                Cancion(
                    1,
                    1,
                    "Scar Tissue",
                    4.56,
                    "No",
                    true,
                    "Anthony Kiedis",
                    "Rick Rubin"
                )
            )

            bibliotecaCanciones.add(
                Cancion(
                    2,
                    2,
                    "Rape Me",
                    2.45,
                    "No",
                    true,
                    "Kurt Cobain",
                    "Rick Rubin"
                )
            )
        }
    }
}