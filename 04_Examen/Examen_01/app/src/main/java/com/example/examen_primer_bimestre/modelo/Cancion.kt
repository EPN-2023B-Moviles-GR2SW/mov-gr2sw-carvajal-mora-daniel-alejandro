package com.example.examen_primer_bimestre.modelo

data class Cancion(
    var id: Int,
    var albumId: Int,
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean = false,
    var escritor: String,
    var productor: String
) {


}
