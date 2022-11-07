package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import android.graphics.Color;
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val lista: ArrayList<Button> = ArrayList();
    var ronda: Int = 0;
    val listaPulsaciones: ArrayList<Button> = ArrayList();
    var contador2: Int = 0;
    var contador: Int = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarPartida();
    }


    private fun iniciarPartida(){
        val botonInicio: Button = findViewById(R.id.inicio);
        if (contador2> 1){
            println("ah no")
            return
        }
        else{
            botonInicio.setOnClickListener() {
                generarSecuencia(lista);
                contador2++
            }
        }
    }

    private fun generarSecuencia(listado: ArrayList<Button>) = runBlocking{
        if (listado.size == 0){
            ronda++
        }
        else{
            for (items in listado){
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                    iluminar(items, ronda, listado)
                }
            }
            ronda++
        }
        when(Random().nextInt(4) + 1){
            1 -> {
                val botonRojo: Button = findViewById(R.id.rojo);

                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(2500L) // non-blocking delay for 1 second (default time unit is ms)
                    listado.add(botonRojo)
                    iluminar(botonRojo, ronda, listado)
                }
            }
            2 -> {
                val botonAmarillo: Button = findViewById(R.id.amarillo);
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(2500L) // non-blocking delay for 1 second (default time unit is ms)
                    listado.add(botonAmarillo)
                    iluminar(botonAmarillo, ronda, listado)
                }
            }
            3 -> {
                val botonVerde: Button = findViewById(R.id.verde);
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(2500L) // non-blocking delay for 1 second (default time unit is ms)
                    listado.add(botonVerde)
                    iluminar(botonVerde, ronda, listado)
                }
            }
            4 -> {
                val botonAzul: Button = findViewById(R.id.azul);
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(2500L) // non-blocking delay for 1 second (default time unit is ms)
                    listado.add(botonAzul)
                    iluminar(botonAzul, ronda, listado)
                }
            }
            else -> {
                Log.d("errorSecuencia", "La secuencia ha dado error")
            }
        }
    }

    private fun iluminar(boton: Button, ronda: Int, listado: ArrayList<Button>) = runBlocking{
        //Problema con el viewbyid a resolver, poner los colores correctamente, hacer que baje el tiempo con ronda
        when(boton){
            boton.findViewById<Button>(R.id.rojo) -> {
                boton.setBackgroundColor(Color.parseColor("#e03a17"))
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                    boton.setBackgroundColor(Color.parseColor("#5A0000"))
                }
                println("a")
            }
            boton.findViewById<Button>(R.id.azul) -> {
                boton.setBackgroundColor(Color.parseColor("#177ae0"))
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                    boton.setBackgroundColor(Color.parseColor("#041E65"))
                }
                println("a")
            }
            boton.findViewById<Button>(R.id.verde) -> {
                boton.setBackgroundColor(Color.parseColor("#21ce16"))
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                    boton.setBackgroundColor(Color.parseColor("#025505"))
                }
                println("a")
            }
            boton.findViewById<Button>(R.id.amarillo) -> {
                boton.setBackgroundColor(Color.parseColor("#e8eb0e"))
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                    boton.setBackgroundColor(Color.parseColor("#685E02"))
                }
            }
        }
        println("Lista: " + listado.size + "ronda: " + ronda)
        if (ronda == listado.size){
            println("Aqui entro")
            val botonInicio: Button = findViewById(R.id.inicio)
            botonInicio.text = "Repite la secuencia"
            for (items in listado){
                println("Contador: " + contador)
                gestionarPulsacion(listado)
            }
        }
    }

    private fun gestionarPulsacion(listado: ArrayList<Button>){

        val botonRojo: Button = findViewById(R.id.rojo);
        val botonAzul: Button = findViewById(R.id.azul);
        val botonVerde: Button = findViewById(R.id.verde);
        val botonAmarillo: Button = findViewById(R.id.amarillo);

        botonRojo.setOnClickListener {
            listaPulsaciones.add(botonRojo)
            contador++
            comprobacion(listado, listaPulsaciones)
        }

        botonAzul.setOnClickListener {
            listaPulsaciones.add(botonAzul)
            contador++
            comprobacion(listado, listaPulsaciones)
        }

        botonVerde.setOnClickListener {
            listaPulsaciones.add(botonVerde)
            contador++
            comprobacion(listado, listaPulsaciones)
        }

        botonAmarillo.setOnClickListener {
            listaPulsaciones.add(botonAmarillo)
            contador++
            comprobacion(listado, listaPulsaciones)
        }

    }

    private fun comprobacion(listado: ArrayList<Button>, listaPulso: ArrayList<Button>){
            if (listado[contador].id != listaPulso[contador].id){
                gameOver();
                println("Alv")
            }
            else if (listado.size == contador + 1){
                generarSecuencia(listado)
                println("No fuco")
                contador = -1

            }
    }

    private fun gameOver(){
        val botonInicio: Button = findViewById(R.id.inicio)
         botonInicio.text = "Has perdido"
         //iniciarPartida()
    }

}