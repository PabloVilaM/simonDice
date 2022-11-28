package com.example.simondice

import androidx.room.*

class RecordDataBase {

    @Database(entities = [RecordEntidades.DataUsuario::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun recordDao(): RecordDao
    }

}