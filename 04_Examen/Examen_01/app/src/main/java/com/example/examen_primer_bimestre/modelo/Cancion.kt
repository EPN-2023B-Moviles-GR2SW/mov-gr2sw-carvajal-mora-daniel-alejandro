package com.example.examen_primer_bimestre.modelo

data class Cancion(
    var id: Int,
    var albumId: Int,
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
) {


}
