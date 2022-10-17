package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import android.graphics.Color;
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val lista: ArrayList<Button> = ArrayList();
    var ronda: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarPartida();
    }


    private fun iniciarPartida(){
        val botonInicio: Button = findViewById(R.id.inicio);
        botonInicio.setOnClickListener(){
            generarSecuencia(lista)
        }
    }

    private fun generarSecuencia(listado: ArrayList<Button>){
        if (listado.size == 0){
            println("Lista nula")
        }
        else{
            for (items in listado){
                  iluminar(items)
            }
        }
        when(Random().nextInt(4) + 1){
            1 -> {
                val botonRojo: Button = findViewById(R.id.rojo);
                iluminar(botonRojo, 0)
                listado.add(botonRojo)

            }
            2 -> {
                val botonAmarillo: Button = findViewById(R.id.amarillo);
                iluminar(botonAmarillo, 0)
                listado.add(botonAmarillo)
            }
            3 -> {
                val botonVerde: Button = findViewById(R.id.verde);
                iluminar(botonVerde, 0)
                listado.add(botonVerde)
            }
            4 -> {
                val botonAzul: Button = findViewById(R.id.azul);
                iluminar(botonAzul, 0)
                listado.add(botonAzul)
            }
            else -> {
                println("Error");
            }
        }
    }

    private fun iluminar(boton: Button, ronda: Int){
        //Problema con el viewbyid a resolver, poner los colores correctamente, hacer que baje el tiempo con ronda
        when(boton){
            boton.findViewById(R.id.rojo) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
            }
            boton.findViewById(R.id.azul) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
            }
            boton.findViewById(R.id.verde) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
            }
            boton.findViewById(R.id.amarillo) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
            }
        }

    }

    private fun visualizaColores(){

    }

}