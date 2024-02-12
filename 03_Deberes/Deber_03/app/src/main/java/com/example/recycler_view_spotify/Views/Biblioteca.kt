package com.example.recycler_view_spotify

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_spotify.Data.BaseDeDatos

class Biblioteca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)
        inicializarRecyclerView()

        // Oculta la Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }


        // Funcionalidad Botones

        val botonHome = findViewById<ImageButton>(R.id.img_btn_Home)
        botonHome.setOnClickListener{irActividad(Home::class.java)}

        val botonTuBiblioteca = findViewById<ImageButton>(R.id.img_btn_Biblioteca)
        botonTuBiblioteca.setOnClickListener{irActividad(Biblioteca::class.java)}


    }


    // Funciones

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


    fun inicializarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.Recycler_Albumes)
        val adaptador = RecyclerViewAdaptadorAlbum(
            this, //Contexto
            BaseDeDatos.arregloAlbumes, // Arreglo datos
            recyclerView // Recycler view
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

}

