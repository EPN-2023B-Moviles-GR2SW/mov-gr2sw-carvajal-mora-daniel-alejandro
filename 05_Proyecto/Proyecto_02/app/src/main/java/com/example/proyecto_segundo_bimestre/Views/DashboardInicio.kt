package com.example.proyecto_segundo_bimestre.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proyecto_segundo_bimestre.R
import com.google.android.material.snackbar.Snackbar

class DashboardInicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_inicio)

        // Verificar si hay un mensaje en el intent
        val mensaje = intent.getStringExtra("mensaje")
        if (mensaje != null) {
            // Mostrar Snackbar con el mensaje
            val rootView: View = findViewById(android.R.id.content)
            Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
        }
    }
}