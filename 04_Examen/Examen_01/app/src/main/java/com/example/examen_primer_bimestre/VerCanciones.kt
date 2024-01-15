package com.example.examen_primer_bimestre

import CancionAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.examen_primer_bimestre.modelo.Album
import com.example.examen_primer_bimestre.modelo.Cancion
import com.example.examen_primer_bimestre.operacionesCRUD.AlbumCRUD
import com.example.examen_primer_bimestre.operacionesCRUD.CancionCRUD
import com.google.android.material.snackbar.Snackbar

class VerCanciones : AppCompatActivity() {

    lateinit var album: Album
    lateinit var listaDeCanciones: List<Cancion>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_canciones)

        // Funcionalidad Botones
        val botonRegresarHomeCanciones = findViewById<Button>(R.id.btn_Regresar_Canciones)
        botonRegresarHomeCanciones.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonAgregarCanciones = findViewById<Button>(R.id.btn_Agregar_Canciones)
        botonAgregarCanciones.setOnClickListener {
            val albumId = intent.getIntExtra("ALBUM_ID", -1)
            irActividadConID(AgregarCancion::class.java, albumId)
        }


        // Obtener el ID del álbum desde el Intent
        val albumId =  intent.getIntExtra("ALBUM_ID", -1)

        // Verificar si se dio un ID Válido
        if (albumId != -1){
            // Obtener el álbum correspondiente desde la base de datos
            album = AlbumCRUD().getById(albumId) ?: Album()

            // Obtener la lista de canciones asociadas al álbum
            listaDeCanciones = CancionCRUD().getCancionesByAlbumId(albumId)

            // Mostrar el nombre del álbum en txt_Inserte_Nombre
            val nombreAlbumTextView = findViewById<TextView>(R.id.txt_Inserte_Nombre)
            nombreAlbumTextView.text = album.nombre

            // Configurar el Adapter para el ListView con el nuevo adaptador
            val adaptadorCanciones = CancionAdapter(this, listaDeCanciones)
            val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
            listViewCanciones.adapter = adaptadorCanciones


            // Obtener el nombre de la canción agregada de los extras del Intent
            val nombreCancionAgregada = intent.getStringExtra("NOMBRE_CANCION_AGREGADA")

            // Verificar si hay un nombre de canción agregada
            if (!nombreCancionAgregada.isNullOrEmpty()) {
                // Mostrar SnackBar con el mensaje de la canción agregada
                mostrarSnackbar("Canción Agregada: $nombreCancionAgregada")
            }
        }

        // Implementar Menú de Opciones en el ListView de Canciones
        val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
        registerForContextMenu(listViewCanciones)

        listViewCanciones.setOnItemClickListener { _, _, position, _ ->
            // Aquí solo necesitas obtener la canción directamente del adaptador
            val cancionSeleccionada = listaDeCanciones[position]
            openContextMenu(listViewCanciones)
        }

    }

    // Crear Menú de Opciones para Canciones
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        // Completar Opciones del Menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cancion, menu)
        // Obtener el índice de la canción seleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        // Acceder a la canción en la posición seleccionada
        val cancionSeleccionada = listaDeCanciones[position]
        // Puedes almacenar el ID de la canción seleccionada si es necesario
        val idCancionSeleccionada = cancionSeleccionada.id


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        // Obtener la canción seleccionada desde el adaptador
        val cancionSeleccionada = listaDeCanciones[position]
        // Almacena el ID de la canción seleccionada si es necesario
        val idCancionSeleccionada = cancionSeleccionada.id

        when (item.itemId) {
            R.id.mi_EditarCancion -> {
                irActividad(EditarCancion::class.java)
                return true
            }
            R.id.mi_EliminarCancion -> {
                // Eliminar la canción seleccionada
                CancionCRUD().eliminarCancionById(idCancionSeleccionada)

                // Actualizar la lista de canciones y el adaptador
                listaDeCanciones = CancionCRUD().getCancionesByAlbumId(album.id)
                val adaptadorCanciones = CancionAdapter(this, listaDeCanciones)
                val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
                listViewCanciones.adapter = adaptadorCanciones

                // Mostrar Snackbar
                mostrarSnackbar("Canción Eliminada")

                return true
            }

            else -> return super.onContextItemSelected(item)
        }
    }

    // Funcion
    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


    fun irActividadConID(clase: Class<*>, albumId: Int) {
        val intent = Intent(this, AgregarCancion::class.java)
        intent.putExtra("ALBUM_ID", albumId)
        startActivity(intent)
    }

    // SnackBar
    private fun mostrarSnackbar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }


}