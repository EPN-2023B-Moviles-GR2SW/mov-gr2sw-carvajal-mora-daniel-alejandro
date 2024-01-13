package com.example.examen_primer_bimestre.modelo

data class Cancion(
    val id: Int = generarId(),
    var nombre: String,
    var duracion: Double,
    var artistaColaborador: String,
    var letra: Boolean,
    var escritor: String,
    var productor: String
) {
    companion object {
        private var contadorId: Int = 1

        private fun generarId(): Int {
            return contadorId++
        }
    }
}
