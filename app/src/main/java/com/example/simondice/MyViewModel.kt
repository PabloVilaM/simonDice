package com.example.simondice

import android.app.Application
import android.icu.text.AlphabeticIndex.Record
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


    //Método para aumentar ronda
    fun aumentarRonda() {
       ronda.value = ronda.value?.plus(1)
    }
    //Método para aumentar record
    fun aumentarRecord() {
        record.value = record.value?.plus(1)
    }
    //Método para resetear ronda y record
    fun resetearRonda(){
        ronda.value = 0
        record.value = 0
    }

    //Método para ver el valor actual del record
    fun verDB(){
        val roomCorrutine = GlobalScope.launch(Dispatchers.Main) {
           rondados.postValue(db.recordDao().mirarElRecord())
            println( "Base de datos record: " + db.recordDao().mirarElRecord())
        }
    }
    //Método para comprobar que el record existe y si no, añadirlo
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

    //Método para actualizar el record en caso de que el record actual sea superior al de la bd
    fun actualizarRecord() {

        val Coroutina = GlobalScope.launch(Dispatchers.Main) {
            println("Problema 2 :" + db.recordDao().mirarElRecord())
            println(record.value.toString() + " a " + ronda.value.toString())
            db!!.recordDao().update(DataUsuario(1,record.value, ronda.value ))
        }
        Coroutina.start()

    }
}