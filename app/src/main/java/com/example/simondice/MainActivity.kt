package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import android.graphics.Color;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonInicio: Button = findViewById(R.id.inicio);
        botonInicio.setOnClickListener(){
            start();
        }
    }

    private fun start() {
        when(Random().nextInt(4) + 1){
              1 -> {
                  val botonRojo: Button = findViewById(R.id.rojo);
                  botonRojo.setBackgroundColor(Color.parseColor("#e03a17"))
              }
              2 -> {

              }
              3 -> {

              }
              4 -> {

              }
            else -> {
                println("Error");
            }
        }
        val botonRojo: Button = findViewById(R.id.rojo);
    }
}