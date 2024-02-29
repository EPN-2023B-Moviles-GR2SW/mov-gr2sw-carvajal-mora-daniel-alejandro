package com.example.proyecto_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_segundo_bimestre.Data.BaseDeDatos
import com.example.proyecto_segundo_bimestre.Data.BaseDeDatos.Companion.arregloJuegosNuevos
import com.example.proyecto_segundo_bimestre.Data.BaseDeDatos.Companion.arregloJuegosPopulares
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R
import com.example.proyecto_segundo_bimestre.RecyclerViews.RecyclerViewAdaptadorFiltroConsolas
import com.example.proyecto_segundo_bimestre.RecyclerViews.RecyclerViewAdaptadorJuegosBuscados
import com.example.proyecto_segundo_bimestre.RecyclerViews.RecyclerViewAdaptadorJuegosPopulares

class BuscarVideojuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_videojuego)
        // Inicializar Recycler Views
        inicializarRecyclerViewConsolas()


        // Funcionalidad Botones
        val btnVolverInicio = findViewById<Button>(R.id.btn_Regresar_Inicio)
        btnVolverInicio.setOnClickListener {
            irActividad(DashboardInicio::class.java)
        }

        val btnBuscar = findViewById<Button>(R.id.btn_Buscar_Videojuego)
        btnBuscar.setOnClickListener {
            val textoBusqueda = findViewById<EditText>(R.id.input_Buscar_Videojuego).text.toString()
            realizarBusqueda(textoBusqueda)
        }
    }

    // Funciones
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun inicializarRecyclerViewConsolas() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_filtrar_consola)

        // Configura el LinearLayoutManager con orientación horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adaptador = RecyclerViewAdaptadorFiltroConsolas(
            this,
            BaseDeDatos.arregloConsola,
            recyclerView
        )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()

        // Agrega un OnClickListener a cada botón
        adaptador.setOnItemClickListener { consola ->
            filtrarPorConsola(consola.nombreConsola)
        }
    }

    fun realizarBusqueda(nombreBusqueda: String) {
        val resultadosPopulares = arregloJuegosPopulares.filter { it.nombreJuego.contains(nombreBusqueda, ignoreCase = true) }
        val resultadosNuevos = arregloJuegosNuevos.filter { it.nombreJuego.contains(nombreBusqueda, ignoreCase = true) }

        val resultadosTotales = ArrayList<Videojuego>().apply {
            addAll(resultadosPopulares)
            addAll(resultadosNuevos)
        }

        actualizarRecyclerView(resultadosTotales)
    }

    fun filtrarPorConsola(nombreConsola: String) {

        val resultadosPopulares = arregloJuegosPopulares.filter { it.plataforma.contains(nombreConsola, ignoreCase = true) }
        val resultadosNuevos = arregloJuegosNuevos.filter { it.plataforma.contains(nombreConsola, ignoreCase = true) }

        val resultadosTotales = ArrayList<Videojuego>().apply {
            addAll(resultadosPopulares)
            addAll(resultadosNuevos)
        }

        actualizarRecyclerView(resultadosTotales)
    }

    fun actualizarRecyclerView(resultados: ArrayList<Videojuego>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_juegos_buscados)
        val adaptador = RecyclerViewAdaptadorJuegosBuscados(this, resultados, recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        adaptador.notifyDataSetChanged()
    }
}