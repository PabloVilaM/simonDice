package com.example.simondice
import androidx.room.*

class RecordDao {
    @Dao
    interface RecordDao {

        @Query("SELECT max(record) FROM datausuario WHERE uid = 1")
        fun mirarElPrimerRecord(): List<RecordEntidades.DataUsuario>

        @Insert
        fun insertAll(vararg users: RecordEntidades.DataUsuario)
    }

}