package com.example.examen_primer_bimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AgregarAlbum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_album)

        // Funcionalidad Botones

        val botonRegresarHome = findViewById<Button>(R.id.btn_Regresar_Home)
        botonRegresarHome.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }



    // Funcion
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}