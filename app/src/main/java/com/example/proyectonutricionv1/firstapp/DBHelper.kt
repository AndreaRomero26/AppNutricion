package com.example.proyectonutricionv1.firstapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.android.material.color.utilities.Cam16

data class DataModel(val value1: String, val value2: String, val value3: String, val value4: String,val value5: String,val value6: String,val value7: String, val value8: String, val value9: String, val value10: String, val value11: String, val value12: String, val value13: String, val value14: String,val value15: String, val value16: String, val value17: String)
data class DataRegistro(val value1: String, val value2: String, val value3: String, val value4: String)


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_VALUE1 TEXT, $COLUMN_VALUE2 TEXT, $COLUMN_VALUE3 TEXT, $COLUMN_VALUE4 TEXT, $COLUMN_VALUE5 TEXT, $COLUMN_VALUE6 TEXT, $COLUMN_VALUE7 TEXT, $COLUMN_VALUE8 TEXT, $COLUMN_VALUE9 TEXT, $COLUMN_VALUE10 TEXT, $COLUMN_VALUE11 TEXT DEFAULT CURRENT_TIMESTAMP, $COLUMN_VALUE12 TEXT, $COLUMN_VALUE13 TEXT, $COLUMN_VALUE14 TEXT, $COLUMN_VALUE15 TEXT, $COLUMN_VALUE16 TEXT, $COLUMN_VALUE17 TEXT)")
        db.execSQL(CREATE_TABLE)
        val CREATE_TABLE_REGISTROS = ("CREATE TABLE $TABLE_REGISTROS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_PACIENTE_ID INTEGER, $COLUMN_VALUE8 TEXT DEFAULT CURRENT_TIMESTAMP, $COLUMN_VALOR TEXT, FOREIGN KEY($COLUMN_PACIENTE_ID) REFERENCES $TABLE_NAME($COLUMN_ID))")
        db.execSQL(CREATE_TABLE_REGISTROS)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REGISTROS")
        onCreate(db)
    }

    fun insertData(value1: String, value2: String, value3: String, value4: String, value5: String,value6: String,value7: String, value8: String, value9: String, value10: String, value12: String, value13: String, value14: String, value15: String, value16: String, value17: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_VALUE1, value1)
        values.put(COLUMN_VALUE2, value2)
        values.put(COLUMN_VALUE3, value3)
        values.put(COLUMN_VALUE4, value4)
        values.put(COLUMN_VALUE5, value5)
        values.put(COLUMN_VALUE6, value6)
        values.put(COLUMN_VALUE7, value7)
        values.put(COLUMN_VALUE8, value8)
        values.put(COLUMN_VALUE9, value9)
        values.put(COLUMN_VALUE10, value10)
        values.put(COLUMN_VALUE12, value12)
        values.put(COLUMN_VALUE13, value13)
        values.put(COLUMN_VALUE14, value14)
        values.put(COLUMN_VALUE15, value15)
        values.put(COLUMN_VALUE16, value16)
        values.put(COLUMN_VALUE17, value17)
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllData(): ArrayList<DataModel> {
        val dataList = ArrayList<DataModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val value1 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE1) })
                val value2 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE2) })
                val value3 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE3) })
                val value4 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE4) })
                val value5 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE5) })
                val value6 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE6) })
                val value7 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE7) })
                val value8 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE8) })
                val value9 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE9) })
                val value10 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE10) })
                val value11 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE11) })
                val value12 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE12) })
                val value13 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE13) })
                val value14 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE14) })
                val value15 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE15) })
                val value16 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE16) })
                val value17 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE17) })
                dataList.add(DataModel(value1, value2, value3, value4,value5,value6,value7, value8, value9, value10, value11, value12, value13, value14, value15, value16, value17))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }
    @SuppressLint("Range")
    fun getNuevoRegistro(folio: String): ArrayList<Triple<String, String, String>> {
        val dataList = ArrayList<Triple<String, String, String>>() // ArrayList de triples de cadenas (String, String, String)
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_VALUE12, $COLUMN_VALUE13, $COLUMN_VALUE14 FROM $TABLE_NAME WHERE $COLUMN_VALUE1='$folio'", null)
        if (cursor.moveToFirst()) {
            do {
                val value1 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE12))
                val value2 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE13))
                val value3 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE14))
                dataList.add(Triple(value1, value2, value3)) // Agregar un triple de valores a dataList
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "HIECH"

        // Tabla Pacientes
        private const val TABLE_NAME = "Pacientes"
        private const val COLUMN_ID = "ID"
        private const val COLUMN_VALUE1 = "Folio"
        private const val COLUMN_VALUE2 = "Municipio"
        private const val COLUMN_VALUE3 = "Localidad"
        private const val COLUMN_VALUE4 = "PrimerApellido"
        private const val COLUMN_VALUE5 = "SegundoApellido"
        private const val COLUMN_VALUE6 = "Nombres"
        private const val COLUMN_VALUE7 = "Nacimiento"
        private const val COLUMN_VALUE8 = "Sexo"
        private const val COLUMN_VALUE9 = "Estatura"
        private const val COLUMN_VALUE10 = "Peso"
        private const val COLUMN_VALUE11 = "MedicionFecha"
        private const val COLUMN_VALUE12 = "BrazoMedida"
        private const val COLUMN_VALUE13 = "Muac"
        private const val COLUMN_VALUE14 = "Inseguridad"
        private const val COLUMN_VALUE15 = "Tutor"
        private const val COLUMN_VALUE16 = "Profesional"
        private const val COLUMN_VALUE17 = "Padrino"

        // Tabla Registros
        private const val TABLE_REGISTROS = "Registros"
        // COLUMN_ID se reutiliza
        // COLUMN_FECHA se reutiliza
        private const val COLUMN_VALOR = "Valor"
        private const val COLUMN_PACIENTE_ID = "paciente_id" // Nueva constante para la FK
    }
}