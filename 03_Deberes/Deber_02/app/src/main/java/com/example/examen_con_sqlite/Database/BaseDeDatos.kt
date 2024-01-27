package com.example.examen_con_sqlite.Database

import com.example.examen_con_sqlite.Model.Album
import com.example.examen_con_sqlite.Model.Cancion

class BaseDeDatos {

    companion object{
        var tablaAlbum: BaseDeDatosHelperAlbum? = null
        var tablaCancion: BaseDeDatosHelperCancion? = null
    }
}