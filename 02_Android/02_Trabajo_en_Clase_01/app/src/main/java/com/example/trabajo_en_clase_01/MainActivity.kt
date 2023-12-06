package com.example.trabajo_en_clase_01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
Realizar una interfaz con qualifiers donde se modifique el texto, color de fondo y color de texto
dependiendo de las siguientes condiciones:

LANDS EN -> Condicion 1
PORT EN -> Condicion 2
LANDS ES -> Condicion 3
PORT ES -> Condicion 4

cada condicion tendra un texto diferente, color de fondo diferente y color de texto diferente
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

