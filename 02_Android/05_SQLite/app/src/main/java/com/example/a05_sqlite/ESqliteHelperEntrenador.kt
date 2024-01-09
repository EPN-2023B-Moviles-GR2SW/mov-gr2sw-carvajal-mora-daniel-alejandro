package com.example.a05_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?, // THIS
    ):SQLiteOpenHelper(contexto,"moviles", null, 1)   // Nombre de la BDD
{

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    // Funcion de Crear Entrenador
    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()    // Guardar los Datos del Registro
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoAGuardar = basedatosEscritura.insert(
            "ENTRENADOR",   // Nombre de la Tabla
                  null,
                    valoresAGuardar // Valores
        )
        basedatosEscritura.close()

        // Si el resultado es -1 significa que NO se guardó el registro
        return if (resultadoAGuardar.toInt() == -1) false else true
    }

    // Funcion Eliminar Entrenador
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        // Where ID = ??
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR",       // Nombre de la Tabla
            "id=?",        // Consulta SQL
            parametrosConsultaDelete
        )
        conexionEscritura
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    // Funcion Actualizar Entrenador
    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        id: Int,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        // Where ID = ??
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR",       // Nombre de la Tabla
            valoresAActualizar,      // Valores
            "id=?",        // Consulta SQL
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }


    // Funcion Buscar Entrenador
    fun consultarEntrenadorPorID(id: Int): BEntrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
            """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,  // Consulta
            parametrosConsultaLectura // Parametros
        )

        // Lógica de Busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) // Indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if (id != null){

                    // Llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arreglo.add(usuarioEncontrado)
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}

