package com.example.simondice

import android.app.Application
import android.content.ContentValues.TAG
import android.icu.text.AlphabeticIndex.Record
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    //private val context = getApplication<Application>().applicationContext
    val ronda = MutableLiveData<Int>()
    val record = MutableLiveData<Int>()
    //val rondados = MutableLiveData<Int>()
   /*val db = Room.databaseBuilder(
        context,
        RecordDB::class.java, "Score"
    ).build()*/

    //Inicializamos la base de datos con la url que nos da la pagina
    val database = Firebase.database("https://simondice-dd113-default-rtdb.europe-west1.firebasedatabase.app/").getReference("record")


    // inicializamos variables cuando instanciamos
    init {
        ronda.value = 0

        //Establecemos el listener de la base de datos y lo añadimos en la úñtima linea
        val Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<Int>()
                Log.d(TAG, "Value is: $value")
                record.value = value!!
                println("Valor es: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        }
        database.addValueEventListener(Listener)
    }


    //Método para aumentar ronda
    fun aumentarRonda() {
       ronda.value = ronda.value?.plus(1)

    }
    //Método para guardar el record
    fun guardarRecord() {
        record.value = ronda.value
        database.setValue(record.value)
    }
    //Método para resetear ronda
    fun resetearRonda(){
        ronda.value = 0
    }


    //Método para ver el valor actual del record
    /*fun verDB(){
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

    }*/
}