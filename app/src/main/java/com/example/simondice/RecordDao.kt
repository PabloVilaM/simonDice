package com.example.simondice
import androidx.room.*

class RecordDao {
    @Dao
    interface RecordDao {

        @Query("SELECT max(record) FROM DataUsuario")
        suspend fun mirarElRecord(): Int

        @Query("INSERT INTO DataUsuario VALUES (0)")
        suspend fun crearPuntuacion()

        @Update
        suspend fun update(record: RecordEntidades.DataUsuario)
    }

}