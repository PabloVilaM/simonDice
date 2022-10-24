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
    val listaPulsaciones: ArrayList<Button> = ArrayList();
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
            ronda++
        }
        else{
            for (items in listado){
                  iluminar(items, ronda, listado)
            }
            ronda++
        }
        when(Random().nextInt(4) + 1){
            1 -> {
                val botonRojo: Button = findViewById(R.id.rojo);
                iluminar(botonRojo, ronda, listado)
                listado.add(botonRojo)

            }
            2 -> {
                val botonAmarillo: Button = findViewById(R.id.amarillo);
                iluminar(botonAmarillo, ronda, listado)
                listado.add(botonAmarillo)
            }
            3 -> {
                val botonVerde: Button = findViewById(R.id.verde);
                iluminar(botonVerde, ronda, listado)
                listado.add(botonVerde)
            }
            4 -> {
                val botonAzul: Button = findViewById(R.id.azul);
                iluminar(botonAzul, ronda, listado)
                listado.add(botonAzul)
            }
            else -> {
                println("Error");
            }
        }
    }

    private fun iluminar(boton: Button, ronda: Int, listado: ArrayList<Button>){
        //Problema con el viewbyid a resolver, poner los colores correctamente, hacer que baje el tiempo con ronda
        when(boton){
            boton.findViewById<Button>(R.id.rojo) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                Thread.sleep(1_000) //1 segundo de espera
                boton.setBackgroundColor(Color.parseColor("#5A0000"))
            }
            boton.findViewById<Button>(R.id.azul) -> {
                boton.setBackgroundColor(Color.parseColor("#177ae0"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#041E65"))
            }
            boton.findViewById<Button>(R.id.verde) -> {
                boton.setBackgroundColor(Color.parseColor("#21ce16"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#025505"))
            }
            boton.findViewById<Button>(R.id.amarillo) -> {
                boton.setBackgroundColor(Color.parseColor("#e8eb0e"))
                Thread.sleep(1_000)
                boton.setBackgroundColor(Color.parseColor("#685E02"))
            }
        }
        if (ronda == listado.size){
            val contador: Int = 0;
            for (items in listado){
                gestionarPulsacion(listado, contador)
            }
        }
    }

    private fun gestionarPulsacion(listado: ArrayList<Button>, contador: Int){

        val botonRojo: Button = findViewById(R.id.rojo);

        botonRojo.setOnClickListener {
            listaPulsaciones.add(botonRojo)
            comprobacion(listado, listaPulsaciones, contador)
        }

    }

    private fun comprobacion(listado: ArrayList<Button>, listaPulso: ArrayList<Button>, contador: Int){
        while (contador < listado.size){
            if (listado[contador].id != listaPulso[contador].id){
                gameOver();
            }
            else{
                generarSecuencia(listado)
            }
        }
    }

    private fun gameOver(){

    }

}