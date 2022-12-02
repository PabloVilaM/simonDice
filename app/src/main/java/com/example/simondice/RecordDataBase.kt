package com.example.simondice

import androidx.room.*
//La base de datos en si
    @Database(
        entities = [DataUsuario::class],
        version = 1
    )
    abstract class RecordDB : RoomDatabase() {
        abstract fun recordDao(): RecordDao
    }

