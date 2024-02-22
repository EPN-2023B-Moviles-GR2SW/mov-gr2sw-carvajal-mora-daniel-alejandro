package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import com.example.examen_segundo_bimestre.Controller.CancionCRUD
import com.example.examen_segundo_bimestre.Model.Cancion
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp

class EditarCancion : AppCompatActivity() {

    private lateinit var cancionId: String
    private lateinit var albumId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cancion)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Obtener ID de la canción y álbum recibidos del Intent
        cancionId = intent.getStringExtra("CANCION_ID") ?: ""
        albumId = intent.getStringExtra("ALBUM_ID") ?: ""

        // Verificar si se recibieron IDs válidos
        if (cancionId.isNotBlank()) {
            // Cargar datos de la canción y llenar la interfaz
            cargarDatosDeLaCancion(cancionId)
        } else {
            // Manejar el caso en el que no se proporcionó un ID de canción válido
            Snackbar.make(
                findViewById(android.R.id.content),
                "No se proporcionó un ID de canción válido",
                Snackbar.LENGTH_SHORT
            ).show()
            finish()
        }

        // Funcionalidad Botones
        val botonRegresarListaCanciones = findViewById<Button>(R.id.btn_Regresar_Listado_Canciones_Editar)
        botonRegresarListaCanciones.setOnClickListener {
            irActividad(VerCanciones::class.java, albumId)
        }

        val botonActualizarCancion = findViewById<Button>(R.id.btn_Actualizar_Cancion)
        botonActualizarCancion.setOnClickListener {
            actualizarCancion()
        }
    }

    // Funciones

    private fun cargarDatosDeLaCancion(cancionId: String) {
        // Obtener la canción de la base de datos o donde se almacene
        val cancionAEditar = CancionCRUD().obtenerCancionPorId(cancionId)

        // Llenar la interfaz con los datos actuales de la canción
        cancionAEditar.addOnSuccessListener { documentSnapshot ->
            val cancion = documentSnapshot.toObject(Cancion::class.java)
            cancion?.let {
                findViewById<TextInputEditText>(R.id.input_Editar_Nombre_Cancion).setText(it.nombre)
                findViewById<TextInputEditText>(R.id.input_Editar_Duracion_Cancion).setText(it.duracion.toString())
                findViewById<TextInputEditText>(R.id.input_Editar_Escritor_Cancion).setText(it.escritor)
                findViewById<TextInputEditText>(R.id.input_Editar_Productor_Cancion).setText(it.productor)
                findViewById<TextInputEditText>(R.id.input_Editar_Colaborador_Cancion).setText(it.artistaColaborador)
                findViewById<Switch>(R.id.sw_Editar_Letra_Cancion).isChecked = it.letra
            }
        }
    }

    private fun actualizarCancion() {
        // Obtener nuevos valores de la interfaz de usuario
        val nuevoNombre = findViewById<TextInputEditText>(R.id.input_Editar_Nombre_Cancion).text.toString()
        val nuevaDuracion = findViewById<TextInputEditText>(R.id.input_Editar_Duracion_Cancion).text.toString().toDoubleOrNull() ?: 0.0
        val nuevoEscritor = findViewById<TextInputEditText>(R.id.input_Editar_Escritor_Cancion).text.toString()
        val nuevoProductor = findViewById<TextInputEditText>(R.id.input_Editar_Productor_Cancion).text.toString()
        val nuevoColaborador = findViewById<TextInputEditText>(R.id.input_Editar_Colaborador_Cancion).text.toString()
        val nuevaLetra = findViewById<Switch>(R.id.sw_Editar_Letra_Cancion).isChecked

        // Validar que no haya campos vacíos
        if (nuevoNombre.isBlank() || nuevoEscritor.isBlank() || nuevoProductor.isBlank()) {
            // Mostrar mensaje de error en un Snackbar
            mostrarSnackbarError("Completa todos los campos.")
        } else {
            // Crear un objeto Cancion con los nuevos valores
            val cancionActualizada = Cancion(
                id = cancionId,
                albumId = albumId,
                nombre = nuevoNombre,
                duracion = nuevaDuracion,
                escritor = nuevoEscritor,
                productor = nuevoProductor,
                artistaColaborador = nuevoColaborador,
                letra = nuevaLetra
            )

            // Actualizar la canción utilizando CancionCRUD
            CancionCRUD().actualizarCancion(cancionActualizada)
                .addOnSuccessListener {
                    // Regresar a la actividad de ver canciones del álbum
                    val intent = Intent(this, VerCanciones::class.java)
                    intent.putExtra("ALBUM_ID", albumId)
                    intent.putExtra("NOMBRE_CANCION_ACTUALIZADA", nuevoNombre) // Agregar el nombre como extra
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    // Manejar el fallo si es necesario
                    e.printStackTrace()
                    mostrarSnackbarError("Error al actualizar la canción.")
                }
        }
    }

    private fun irActividad(clase: Class<*>, albumId: String) {
        val intent = Intent(this, clase)
        intent.putExtra("ALBUM_ID", albumId)
        startActivity(intent)
    }

    // SnackBar
    private fun mostrarSnackbarError(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}
