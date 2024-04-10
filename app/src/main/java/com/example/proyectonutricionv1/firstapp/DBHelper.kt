package com.example.proyectonutricionv1.firstapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//data class es la clase que regresa getAllData y getPacientePorFolio con los campos de la DB
data class dataModel(
    val value1: String,
    val value2: String,
    val value3: String,
    val value4: String,
    val value5: String,
    val value6: String,
    val value7: String,
    val value8: String,
    val value9: String,
    val value10: String,
    val value11: String,
    val value12: Double,
    val value13: String,
    val value14: String,
    val value15: String,
    val value16: String,
    val value17: String,
    val value18: String,
    val value19: String,
    val value20: String)

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //Creacion de tablas, solo si no existen
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_VALUE1 TEXT, $COLUMN_VALUE2 TEXT, $COLUMN_VALUE3 TEXT, $COLUMN_VALUE4 TEXT, $COLUMN_VALUE5 TEXT, $COLUMN_VALUE6 TEXT, $COLUMN_VALUE7 TEXT, $COLUMN_VALUE8 TEXT, $COLUMN_VALUE9 TEXT, $COLUMN_VALUE10 TEXT, $COLUMN_VALUE11 TEXT DEFAULT CURRENT_TIMESTAMP, $COLUMN_VALUE12 REAL, $COLUMN_VALUE13 TEXT, $COLUMN_VALUE14 TEXT, $COLUMN_VALUE15 TEXT, $COLUMN_VALUE16 TEXT, $COLUMN_VALUE17 TEXT, $COLUMN_VALUE18 TEXT DEFAULT CURRENT_TIMESTAMP, $COLUMN_VALUE19 TEXT, $COLUMN_VALUE20 TEXT)")
        db.execSQL(CREATE_TABLE)
        val CREATE_TABLE_REGISTROS = ("CREATE TABLE $TABLE_REGISTROS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_VALUE1 INTEGER, $COLUMN_VALUE11 TEXT DEFAULT CURRENT_TIMESTAMP, $COLUMN_VALUE12 TEXT, $COLUMN_VALUE13 TEXT, FOREIGN KEY($COLUMN_VALUE1) REFERENCES $TABLE_NAME($COLUMN_VALUE1))")
        db.execSQL(CREATE_TABLE_REGISTROS)

        //El trigger es para actualizar la tabla principal cuando se ingresa un registro en table registros
        val CREATE_TRIGGER_UPDATE_PACIENTE = """
        CREATE TRIGGER update_paciente_after_insert_registro
        AFTER INSERT ON $TABLE_REGISTROS
        BEGIN
            UPDATE $TABLE_NAME
            SET $COLUMN_VALUE11 = NEW.$COLUMN_VALUE11, 
                $COLUMN_VALUE12 = NEW.$COLUMN_VALUE12,
                $COLUMN_VALUE13 = NEW.$COLUMN_VALUE13
            WHERE $COLUMN_VALUE1 = NEW.$COLUMN_VALUE1;
        END;
    """.trimIndent()

        db.execSQL(CREATE_TRIGGER_UPDATE_PACIENTE)

    }

    //Metodo por si tuvieramos que borrar las tablas
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REGISTROS")
        onCreate(db)
    }

    //Metodo para insertar los datos de cada paciente en la tabla principal
    fun insertData(value1: String, value2: String, value3: String, value4: String, value5: String,value6: String,value7: String, value8: String, value9: String, value10: String, value12: Double, value13: String, value14: String, value15: String, value16: String, value17: String, value19: String, value20: String): Boolean {
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
        values.put(COLUMN_VALUE19, value19)
        values.put(COLUMN_VALUE20, value20)
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id != -1L
    }

    //Metodo para insertar los valores en la tabla de registros, para el progreso de los pacientes
    fun insertRegistro(folio: String, nuevoBrazo: Double, muacNuevo: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_VALUE1, folio)
            put(COLUMN_VALUE12, nuevoBrazo)
            put(COLUMN_VALUE13, muacNuevo)
        }
        val id = db.insert(TABLE_REGISTROS, null, values)
        db.close()
        return id !=-1L
    }

    //Metodo para recuperar toda la información de la tabla principal y mostrarla en la GUI
    @SuppressLint("Range")
    fun getAllData(): ArrayList<dataModel> {
        val dataList = ArrayList<dataModel>()
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
                val value12 = cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE12))
                val value13 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE13) })
                val value14 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE14) })
                val value15 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE15) })
                val value16 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE16) })
                val value17 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE17) })
                val value18 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE18) })
                val value19 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE19) })
                val value20 = cursor.getString(with(cursor) { getColumnIndex(COLUMN_VALUE20) })
                dataList.add(dataModel(value1, value2, value3, value4,value5,value6,value7, value8, value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19, value20))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }

    //Metodo para recuperar la medida de brazo anterior asi como inseguridad y muac.Para que se
    //muestren estos datos en la activity de registrar progreso
    @SuppressLint("Range")
    fun getDataToNuevoRegistro(folio: String): ArrayList<Triple<Double, String, String>> {
        val dataList = ArrayList<Triple<Double, String, String>>() // ArrayList de triples de cadenas (String, String, String)
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_VALUE12, $COLUMN_VALUE13, $COLUMN_VALUE14 FROM $TABLE_NAME WHERE $COLUMN_VALUE1='$folio'", null)
        if (cursor.moveToFirst()) {
            do {
                val value1 = cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE12))
                val value2 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE13))
                val value3 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE14))
                dataList.add(Triple(value1, value2, value3)) // Agregar un triple de valores a dataList
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }

    //Metodo para recuperar la dosis del paciente
    @SuppressLint("Range")
    fun getDosis(folio: String): ArrayList<Triple<String, String, String>> {
        val dataList = ArrayList<Triple<String, String, String>>() // ArrayList de triples de cadenas (String, String, String)
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_VALUE18, $COLUMN_VALUE19, $COLUMN_VALUE20 FROM $TABLE_NAME WHERE $COLUMN_VALUE1='$folio'", null)
        if (cursor.moveToFirst()) {
            do {
                val value1 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE18))
                val value2 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE19))
                val value3 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE20))
                dataList.add(Triple(value1, value2, value3)) // Agregar un triple de valores a dataList
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return dataList
    }

    //Metodo para eliminar un registro de la base de datos principal
    fun eliminarRegistro(folio: String) {
        val db = this.writableDatabase // Obtienes una instancia de la base de datos en modo escritura
        db.delete("$TABLE_NAME", "Folio = ?", arrayOf(folio)) // Eliminas el registro cuyo Folio coincida con el argumento
        db.close() // Cierras la conexión a la base de datos
    }

    //Metodo para actualizar valores de la tabla principal
    fun update(folio: String, value2: String, value3: String, value4: String, value5: String,value6: String,value7: String, value8: String, value9: String, value10: String, value15: String, value16: String, value17: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_VALUE2, value2)
        values.put(COLUMN_VALUE3, value3)
        values.put(COLUMN_VALUE4, value4)
        values.put(COLUMN_VALUE5, value5)
        values.put(COLUMN_VALUE6, value6)
        values.put(COLUMN_VALUE7, value7)
        values.put(COLUMN_VALUE8, value8)
        values.put(COLUMN_VALUE9, value9)
        values.put(COLUMN_VALUE10, value10)
        values.put(COLUMN_VALUE15, value15)
        values.put(COLUMN_VALUE16, value16)
        values.put(COLUMN_VALUE17, value17)
        val rowCount = db.update("$TABLE_NAME", values, "folio = ?", arrayOf(folio))
        db.close()
        // Retorna true si rowCount es mayor que 0, indicando que se actualizaron filas
        return rowCount > 0
    }


    //Metodo para recuperar los datos de un paciente de acuerdo a su folio
    @SuppressLint("Range")
    fun getPacientePorFolio(folio: String): dataModel? {
        val db = this.readableDatabase
        var paciente: dataModel? = null

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_VALUE1 = ?", arrayOf(folio))
        if (cursor.moveToFirst()) {
            val value1 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE1))
            val value2 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE2))
            val value3 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE3))
            val value4 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE4))
            val value5 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE5))
            val value6 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE6))
            val value7 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE7))
            val value8 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE8))
            val value9 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE9))
            val value10 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE10))
            val value11 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE11))
            val value12 = cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE12))
            val value13 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE13))
            val value14 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE14))
            val value15 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE15))
            val value16 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE16))
            val value17 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE17))
            val value18 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE18))
            val value19 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE19))
            val value20 = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE20))

            paciente = dataModel(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19, value20)
        }

        cursor.close()
        db.close()
        return paciente
    }

    fun getValoresPorFolio(folio: String): List<Float> {
        val valoresList = mutableListOf<Float>()
        val db = this.readableDatabase

        val projection = arrayOf("$COLUMN_VALUE12")
        val selection = "Folio = ?"
        val selectionArgs = arrayOf(folio)

        val cursor = db.query(
            "$TABLE_REGISTROS",
            projection,         // Las columnas que quieres devolver
            selection,          // Las columnas para la cláusula WHERE
            selectionArgs,      // Los valores para la cláusula WHERE
            null,      // No agrupar las filas
            null,       // No filtrar por grupos de filas
            null        // El orden del sorteo
        )

        with(cursor) {
            while (moveToNext()) {
                val valor = getFloat(getColumnIndexOrThrow("$COLUMN_VALUE12"))
                valoresList.add(valor)
            }
        }
        cursor.close()
        db.close()

        return valoresList
    }

    //Nombres de DB, tablas y columnas
    companion object {
        private const val DATABASE_VERSION = 2
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
        private const val COLUMN_VALUE18 = "InstruFecha"
        private const val COLUMN_VALUE19 = "Cantidad"
        private const val COLUMN_VALUE20 = "Dosis"

        // Tabla Registros
        private const val TABLE_REGISTROS = "Registros"
        // COLUMN_ID se reutiliza
        // COLUMN_VALUE1 folio se reutiliza
        // COLUMN_FECHA se reutiliza
        // COLUMN_VALUE12 brazo medida se reutiliza
        // COLUMN_VALUE13 Muac se reutiliza
    }
}