package com.example.proyecto_segundo_bimestre.Data

import com.example.proyecto_segundo_bimestre.Model.Consola
import com.example.proyecto_segundo_bimestre.Model.Videojuego
import com.example.proyecto_segundo_bimestre.R

class BaseDeDatos {

    companion object{
        val arregloJuegosPopulares = arrayListOf<Videojuego>()
        val arregloJuegosNuevos = arrayListOf<Videojuego>()
        val arregloConsola = arrayListOf<Consola>()

        // Datos Quemados para el Recycler View de Videojuegos Populares
        init{
            arregloJuegosPopulares.add(Videojuego("Zelda Breath Of The Wild","Mar 03,2017","Nintendo Switch","Nintendo", "Nintendo", R.drawable.zelda_botw))
            arregloJuegosPopulares.add(Videojuego("Forza Horizon 5", "Nov 01,2021","Xbox Series X","Microsoft Studios", "Microsoft", R.drawable.forza_horizon_5))
            arregloJuegosPopulares.add(Videojuego("Red Dead Redemption 2","Oct 26,2018", "PlayStation 4", "Rockstar Games", "2K Interactive",R.drawable.red_dead_redemption_2))
            arregloJuegosPopulares.add(Videojuego("Bloodborne", "Mar 24,2015", "PlayStation 4","From Software", "Bandai Namco", R.drawable.bloodborne))
            arregloJuegosPopulares.add(Videojuego("EA Sports FC 24", "Sep 29,2023", "PlayStation 5","Electronic Arts", "Electronic Arts", R.drawable.fifa_24))
            arregloJuegosPopulares.add(Videojuego("The Last Of Us", "Jun 14,2013", "PlayStation 3", "Naughty Dog", "Sony Interactive", R.drawable.tlou))
        }

        // Datos Quemados para el Recycler View de Videojuegos Recientes
        init{
            arregloJuegosNuevos.add(Videojuego("Final Fantasy 7 Remake", "Feb 29,2024", "PlayStation 5", "Square Enix", "Square Enix", R.drawable.ff7_remake))
            arregloJuegosNuevos.add(Videojuego("Persona 3 Reload", "Feb 02, 2024", "Xbox Series X", "Atlus", "Sega",R.drawable.persona_3_reload))
            arregloJuegosNuevos.add(Videojuego("Baldur's Gate 3", "Ago 03,2023", "Steam", "Larian Studios", "Valve",R.drawable.baldurs_gate))
            arregloJuegosNuevos.add(Videojuego("Mario vs Donkey Kong", "Feb 16,2024", "Nintendo Switch", "Nintendo", "Nintendo",R.drawable.mario_vs_donkey))
            arregloJuegosNuevos.add(Videojuego("The Last Of Us Part II", "Jun 19,2020", "PlayStation 4", "Naughty Dog", "Sony Interactive",R.drawable.tlou_2))
            arregloJuegosNuevos.add(Videojuego("Super Mario RPG", "Nov 17,2023", "Nintendo Switch", "Square Enix", "Nintendo",R.drawable.super_mario))
        }

        // Datos Quemados para el Recycler View de Filtrar por Consola
        init{
            arregloConsola.add(Consola("PlayStation"))
            arregloConsola.add(Consola("Xbox"))
            arregloConsola.add(Consola("Nintendo"))
            arregloConsola.add(Consola("Steam"))
        }
    }
}