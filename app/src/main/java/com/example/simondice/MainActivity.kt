package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import android.graphics.Color;
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    //Lista de botones donde guardaremos que botones si iluminan
    val lista: ArrayList<Button> = ArrayList();
    //Lista en la que guardamos las pulsaciones
    val listaPulsaciones: ArrayList<Button> = ArrayList();
    //Contador para comprobar luego si la id de las pulsaciones y de la iluminacion coincide y por ende es acierto oh no
    var contador: Int = -1;
    //Objeto del viewmodel para trabajar con las variables de ronda, record y posteriormente bd's
    val miModelo by viewModels<MyViewModel>()


    //Función que se ejecuta al iniciar la aplicación, llama a iniciar partida
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarPartida();
    }


    //Al iniciar la partida colocamos un listener en el boton de inicio. Al pulsarlo genera una secuencia.
    private fun iniciarPartida(){
            val botonInicio: Button = findViewById(R.id.inicio)
            botonInicio.setOnClickListener() {
                generarSecuencia(lista);

            }

    }

    //Como siempre se va a generar un color nuevo en la secuencia es lo primero que hacemos con el switch
    //es generar un color, ponemos los escuchas y los añadimos a la lista, luego recorremos la lista
    // y por cada boton requerido se ilumina, además de estar con una corutina para lanzar los que sean
    //del mismo color con mas retraso y que no se solapen.
    private fun generarSecuencia(listado: ArrayList<Button>) = runBlocking{
        when(Random().nextInt(4) + 1){
            1 -> {
                val botonRojo: Button = findViewById(R.id.rojo);
                listado.add(botonRojo)

            }
            2 -> {
                val botonAmarillo: Button = findViewById(R.id.amarillo);
                    listado.add(botonAmarillo)
            }
            3 -> {
                val botonVerde: Button = findViewById(R.id.verde);
                listado.add(botonVerde)

            }
            4 -> {
                val botonAzul: Button = findViewById(R.id.azul);
                listado.add(botonAzul)

            }
            else -> {
                Log.d("errorSecuencia", "La secuencia ha dado error")
            }
        }
            var boton: Button = findViewById(R.id.rojo)
                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine and continue
                    for (items in listado){
                        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
                        if(boton == items) {
                            delay(500L)
                        }
                        boton = items
                        iluminar(items, miModelo.ronda.value!!.toInt(), listado)
                    }
                }
            miModelo.aumentarRonda()
            miModelo.aumentarRecord()


    }

    //Con el boton que le pasamos, junto a la ronda y la lista lo que haremos será comprobar que botón
    //es el mandado, iluminando ese durante el tiempo puesto en el delay de la corutina, que al acabar
    //ese delay regresa al color normal. Al acabar esto le indicamos que repita la secuencia y por cada
    //boton generamos escuchas en el siguiente metodo.
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

    //Generamos los escuchas, los añadimos a la lista de pulsaciones y comprobamos si dicha pulsación
    //es correcta.
    private fun gestionarPulsacion(listado: ArrayList<Button>){

        val botonRojo: Button = findViewById(R.id.rojo);
        val botonAzul: Button = findViewById(R.id.azul);
        val botonVerde: Button = findViewById(R.id.verde);
        val botonAmarillo: Button = findViewById(R.id.amarillo);
        println("Tam: " + listaPulsaciones.size)
        botonRojo.setOnClickListener {
            listaPulsaciones.add(botonRojo)
            println(listaPulsaciones.size)
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
        miModelo.verDB()
    }

    //Comprobamos con el contador en ambas listas si coincide la id, es decir, el color
    //Al hacerlo, si no coinciden le mandamos al gameover, si el tamaño de la lista es
    //el del contador + 1, significará que lo habrá hecho bien, por ende limpiamos la lista de pulsaciones
    //y volvemos  generar secuencia.
    private fun comprobacion(listado: ArrayList<Button>, listaPulso: ArrayList<Button>){

            if (listado[contador].id != listaPulso[contador].id){
                gameOver(listado);
                println("Alv")
            }
            else if (listado.size == contador + 1){
                generarSecuencia(listado)
                println("No fuco")
                contador = -1
                listaPulso.clear()

            }
        println("abc")
    }

    //Cuando el usuario falla, buscamos que salga el record en pantalla, además de limpiar las variables
    //Y poner a cero todas las cosas.
    private fun gameOver(listado: ArrayList<Button>){
        val botonInicio: Button = findViewById(R.id.inicio)
        val record: Int = miModelo.rondados.value!!.toInt()
        if (record < miModelo.record.value!!.toInt()){
            miModelo.actualizarRecord()
            println("Actualizado" + miModelo.record.value.toString())
        }
        miModelo.actRecordBD()
         botonInicio.text = "Has perdido, ronda:" + miModelo.ronda.value.toString() + " Record:" + miModelo.rondados.value.toString()
        listaPulsaciones.clear()
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
            miModelo.resetearRonda()
        }
        contador = -1
        listado.clear()
        iniciarPartida()
    }

}