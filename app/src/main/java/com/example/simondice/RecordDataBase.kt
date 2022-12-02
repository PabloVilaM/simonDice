package com.example.simondice

import androidx.room.*

    @Database(
        entities = [DataUsuario::class],
        version = 1
    )
    abstract class RecordDB : RoomDatabase() {
        abstract fun recordDao(): RecordDao
    }

