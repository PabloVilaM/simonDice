package com.example.simondice
import androidx.room.*

//Los métodos en relacion con la tabla y busquedas en la bd
    @Dao
    interface RecordDao {

        @Query("SELECT max(record) FROM DataUsuario")
        suspend fun mirarElRecord(): Int

        @Query("INSERT INTO DataUsuario VALUES (1,1,1)")
        suspend fun crearPuntuacion()

        @Update
        suspend fun update(record: DataUsuario)
    }

