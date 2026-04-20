package com.example.marinaaprendeamultiplicar.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MarinaMultiplica"
        private const val DATABASE_VERSION = 1

        // Estructura de la tabla
        const val TABLE_ESTADO = "ESTADO"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_MEJOR_TIEMPO = "MejorTiempo"
        const val COLUMN_VECES_BATIDO = "VecesBatido"
        const val COLUMN_VECES_SUPERADO = "VecesSuperado"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // 1. Crear la tabla
        val createTable = ("CREATE TABLE " + TABLE_ESTADO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NOMBRE + " TEXT,"
                + COLUMN_MEJOR_TIEMPO + " REAL,"
                + COLUMN_VECES_BATIDO + " INTEGER,"
                + COLUMN_VECES_SUPERADO + " INTEGER" + ")")
        db?.execSQL(createTable)

        // 2. Inicializar los 11 registros fijos
        initTablas(db)
    }

    private fun initTablas(db: SQLiteDatabase?) {
        for (i in 1..11) {
            val values = ContentValues()
            values.put(COLUMN_ID, i)

            // Asignar nombre según el ID
            if (i <= 10) {
                values.put(COLUMN_NOMBRE, "Tabla del $i")
            } else {
                values.put(COLUMN_NOMBRE, "Todas las tablas")
            }

            values.put(COLUMN_MEJOR_TIEMPO, 99.99)
            values.put(COLUMN_VECES_BATIDO, 0)
            values.put(COLUMN_VECES_SUPERADO, 0)

            db?.insert(TABLE_ESTADO, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ESTADO")
        onCreate(db)
    }
}