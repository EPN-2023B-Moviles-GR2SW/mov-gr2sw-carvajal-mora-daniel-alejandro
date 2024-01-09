package com.example.a05_sqlite

class BEntrenador (
    var id: Int,
    var nombre: String?,
    var descripcion: String?
){
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}