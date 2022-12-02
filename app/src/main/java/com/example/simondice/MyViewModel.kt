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
    val rondados = MutableLiveData<Int>()
   val db = Room.databaseBuilder(
        context,
        RecordDB::class.java, "Score"
    ).build()


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
        record.value = 0
    }

    fun verDB(){
        val roomCorrutine = GlobalScope.launch() {
           rondados.postValue(db.recordDao().mirarElRecord())
            println( "da" + db.recordDao().mirarElRecord())
        }
    }
    fun actRecordBD(){
        val roomCorrutine = GlobalScope.launch() {
            try {
                record.postValue(db.recordDao().mirarElRecord())
                println("Problema:" + db.recordDao().mirarElRecord())
            } catch(ex : NullPointerException) {
                db!!.recordDao().crearPuntuacion()
                println("Entro en el catch")
                record.postValue(db.recordDao().mirarElRecord())
            }
        }
        roomCorrutine.start()
    }

    fun añadirRecord() {

        val Coroutina = GlobalScope.launch() {
            println("Problema2 :" + db.recordDao().mirarElRecord())
            println(record.value.toString() + " a " + ronda.value.toString())
            db!!.recordDao().update(DataUsuario(1,record.value, ronda.value ))
        }
        Coroutina.start()

    }


}