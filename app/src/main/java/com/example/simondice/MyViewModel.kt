package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG_LOG: String = "Aqui tenemoh el ViewModel"

    val ronda = MutableLiveData<Int>()
    val record = MutableLiveData<Int?>()


    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos el record y la ronda")
        record.value = 0
        ronda.value = 0
    }


    fun aumentarRonda() {
       ronda.value = ronda.value?.plus(1)
    }

    fun aumentarRecord() {
        record.value = record.value?.plus(1)
    }

    fun resetearRonda(){
        ronda.value = 0
    }
}