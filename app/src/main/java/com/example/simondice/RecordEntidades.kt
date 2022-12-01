package com.example.simondice
    import androidx.room.*

class RecordEntidades {
    @Entity
    data class DataUsuario(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "record") val record: Int?,
        @ColumnInfo(name = "ronda") val ronda: Int?
    )

}