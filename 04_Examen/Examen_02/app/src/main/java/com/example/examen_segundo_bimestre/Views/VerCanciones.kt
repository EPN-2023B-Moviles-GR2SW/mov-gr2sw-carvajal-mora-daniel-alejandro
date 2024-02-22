package com.example.examen_segundo_bimestre.Views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_segundo_bimestre.Controller.CancionAdapter
import com.example.examen_segundo_bimestre.Controller.CancionCRUD
import com.example.examen_segundo_bimestre.Model.Cancion
import com.example.examen_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener


class VerCanciones : AppCompatActivity() {

    lateinit var adaptador: CancionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_canciones)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Obtener el ID del álbum de los extras del Intent
        val albumId = intent.getIntExtra("ALBUM_ID", -1)

        // Obtener referencias a los elementos de la interfaz de usuario
        val listViewCanciones = findViewById<ListView>(R.id.lv_Listado_Canciones)
        val botonAgregarCancion = findViewById<Button>(R.id.btn_Agregar_Canciones)

        // Crear el Adapter
        CancionCRUD().obtenerCancionesPorAlbumId(albumId.toString())
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    val listadoDeCanciones = mutableListOf<Cancion>()
                    val querySnapshot = task.result

                    if (querySnapshot != null) {
                        for (document in querySnapshot) {
                            val cancion = Cancion.fromSnapshot(document)
                            listadoDeCanciones.add(cancion)
                        }
                    }

                    adaptador = CancionAdapter(this, listadoDeCanciones)
                    listViewCanciones.adapter = adaptador
                } else {
                    // Manejar el fallo si es necesario
                    task.exception?.printStackTrace()
                    mostrarSnackbar("Error al obtener canciones del álbum.")
                }
            })

        // Funcionalidad Botón Agregar Canción
        botonAgregarCancion.setOnClickListener {
            val intent = Intent(this, AgregarCancion::class.java)
            intent.putExtra("ALBUM_ID", albumId)
            startActivity(intent)
        }
    }

    // SnackBar
    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}
