package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen_segundo_bimestre.Controller.AlbumCRUD
import com.example.examen_segundo_bimestre.Model.Album
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp



class AgregarAlbum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_album)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Obtener referencias a los elementos de la interfaz
        val inputNombreAlbum = findViewById<EditText>(R.id.input_Nombre_Album)
        val inputArtistaAlbum = findViewById<EditText>(R.id.input_Artista_Album)
        val inputGeneroAlbum = findViewById<EditText>(R.id.input_Genero_Album)
        val inputPrecioAlbum = findViewById<EditText>(R.id.input_Precio_Album)
        val inputAnioAlbum = findViewById<EditText>(R.id.input_Anio_Album)
        val switchExplicitoAlbum = findViewById<Switch>(R.id.sw_Explicito_Album)

        // Funcionalidad Botones
        val botonRegresarHome = findViewById<Button>(R.id.btn_Regresar_Home)
        botonRegresarHome.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonCrearAlbum = findViewById<Button>(R.id.btn_Crear_Album)
        botonCrearAlbum.setOnClickListener {
            // Obtener los valores ingresados por el usuario
            val nombreAlbum = inputNombreAlbum.text.toString()
            val artistaAlbum = inputArtistaAlbum.text.toString()
            val generoAlbum = inputGeneroAlbum.text.toString()
            val precioAlbum = inputPrecioAlbum.text.toString().toDoubleOrNull() ?: 0.0
            val anioAlbum = inputAnioAlbum.text.toString().toIntOrNull() ?: 0
            val esExplicito = switchExplicitoAlbum.isChecked

            // Validar que se ingresen valores válidos
            if (validarEntradas(nombreAlbum, artistaAlbum, anioAlbum, precioAlbum)) {
                // Crear un objeto Album con los valores ingresados
                val nuevoAlbum = Album(
                    id = null,  // Puedes dejar el ID como null si el constructor lo permite
                    anioLanzamiento = anioAlbum,
                    artista = artistaAlbum,
                    esExplicito = esExplicito,
                    genero = generoAlbum,
                    nombre = nombreAlbum,
                    precio = precioAlbum
                )

                // Llamada a la función para agregar el álbum
                agregarAlbum(nuevoAlbum)
            } else {
                mostrarSnackbar("Por favor, ingrese valores válidos.")
            }
        }
    }

    private fun validarEntradas(
        nombreAlbum: String,
        artistaAlbum: String,
        anioAlbum: Int,
        precioAlbum: Double
    ): Boolean {
        return nombreAlbum.isNotEmpty() && artistaAlbum.isNotEmpty() && anioAlbum > 0 && precioAlbum >= 0
    }

    private fun agregarAlbum(album: Album) {
        AlbumCRUD(this).crearAlbum(album)
            .addOnSuccessListener { documentReference ->
                // Éxito al agregar el álbum
                mostrarSnackbar("Álbum agregado exitosamente.")
                // Devolver el ID del documento del álbum agregado a la actividad principal
                devolverResultado(documentReference.id, album.nombre)
            }
            .addOnFailureListener { e ->
                // Manejar el fallo si es necesario
                e.printStackTrace()
                mostrarSnackbar("Error al agregar el álbum.")
            }
    }

    private fun devolverResultado(albumId: String, nombreAlbum: String) {
        // Devolver el nombre del álbum a la actividad principal
        val intent = Intent()
        intent.putExtra("NOMBRE_ALBUM_AGREGADO", nombreAlbum)
        intent.putExtra("ALBUM_ID_AGREGADO", albumId)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun mostrarSnackbar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
