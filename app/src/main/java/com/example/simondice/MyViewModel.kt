package com.example.simondice

import android.app.Application
import android.icu.text.AlphabeticIndex.Record
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG_LOG: String = "Aqui tenemoh el ViewModel"
    private val context = getApplication<Application>().applicationContext
    val ronda = MutableLiveData<Int>()
    val record = MutableLiveData<Int?>()
    val db = Room.databaseBuilder(
        context,
        RecordDataBase.AppDatabase::class.java, "Score"
    ).build()
    val recordDao = db.recordDao()


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

    /*fun actRecordBD(){
        val roomCorrutine = GlobalScope.launch() {
            try {
                record.value = recordDao.mirarElRecord()
            } catch(ex : NullPointerException) {
                db!!.recordDao().crearPuntuacion()
                record.value = db!!.recordDao().mirarElRecord()
            }
        }
        roomCorrutine.start()
    }

    fun añadirRecord() {

        val Coroutina = GlobalScope.launch() {
            db!!.recordDao().update(RecordEntidades.DataUsuario(1,record.value, ronda.value ))
        }
        Coroutina.start()

    }*/


}