package com.example.proyectonutricionv1.firstapp.staff

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.TypedValue
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.example.proyectonutricionv1.firstapp.dataModel
import java.io.File
import java.io.IOException

class ExpedientesActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var btnExcel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expedientes)

        btnExcel = findViewById<Button>(R.id.buttonExcel)

        dbHelper = DBHelper(this)
        val dataList = dbHelper.getAllData()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)

        val textSizeSP = 20f

        for ((value1, value2, value3,value4, value5, value6, value7, value8, value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19, value20) in dataList) {
            val tableRow = TableRow(this)
            val layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            tableRow.layoutParams = layoutParams

            val textView1 = TextView(this)
            textView1.text = value1
            textView1.setPadding(10, 10, 10, 10)
            textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView2 = TextView(this)
            textView2.text = value2
            textView2.setPadding(10, 10, 10, 10)
            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView3 = TextView(this)
            textView3.text = value3
            textView3.setPadding(10, 10, 10, 10)
            textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView4 = TextView(this)
            textView4.text = value4
            textView4.setPadding(10, 10, 10, 10)
            textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView5 = TextView(this)
            textView5.text = value5
            textView5.setPadding(10, 10, 10, 10)
            textView5.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView6 = TextView(this)
            textView6.text = value6
            textView6.setPadding(10, 10, 10, 10)
            textView6.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView7 = TextView(this)
            textView7.text = value7
            textView7.setPadding(10, 10, 10, 10)
            textView7.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView8 = TextView(this)
            textView8.text = value8
            textView8.setPadding(10, 10, 10, 10)
            textView8.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView9 = TextView(this)
            textView9.text = value9
            textView9.setPadding(10, 10, 10, 10)
            textView9.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView10 = TextView(this)
            textView10.text = value10
            textView10.setPadding(10, 10, 10, 10)
            textView10.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView11 = TextView(this)
            textView11.text = value11
            textView11.setPadding(10, 10, 10, 10)
            textView11.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView12 = TextView(this)
            textView12.text = value12.toString()
            textView12.setPadding(10, 10, 10, 10)
            textView12.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView13 = TextView(this)
            textView13.text = value13
            textView13.setPadding(10, 10, 10, 10)
            textView13.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView14 = TextView(this)
            textView14.text = value14
            textView14.setPadding(10, 10, 10, 10)
            textView14.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView15 = TextView(this)
            textView15.text = value15
            textView15.setPadding(10, 10, 10, 10)
            textView15.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView16 = TextView(this)
            textView16.text = value16
            textView16.setPadding(10, 10, 10, 10)
            textView16.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView17 = TextView(this)
            textView17.text = value17
            textView17.setPadding(10, 10, 10, 10)
            textView17.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView18 = TextView(this)
            textView18.text = value18
            textView18.setPadding(10, 10, 10, 10)
            textView18.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView19 = TextView(this)
            textView19.text = value19
            textView19.setPadding(10, 10, 10, 10)
            textView19.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
            val textView20 = TextView(this)
            textView20.text = value20
            textView20.setPadding(10, 10, 10, 10)
            textView20.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)

            tableRow.addView(textView1)
            tableRow.addView(textView2)
            tableRow.addView(textView3)
            tableRow.addView(textView4)
            tableRow.addView(textView5)
            tableRow.addView(textView6)
            tableRow.addView(textView7)
            tableRow.addView(textView8)
            tableRow.addView(textView9)
            tableRow.addView(textView10)
            tableRow.addView(textView11)
            tableRow.addView(textView12)
            tableRow.addView(textView13)
            tableRow.addView(textView14)
            tableRow.addView(textView15)
            tableRow.addView(textView16)
            tableRow.addView(textView17)
            tableRow.addView(textView18)
            tableRow.addView(textView19)
            tableRow.addView(textView20)

            tableLayout.addView(tableRow)
        }

        btnExcel.setOnClickListener {
            val dataList = dbHelper.getAllData()

            // Convertir los datos a formato CSV
            val csvData = convertirTablaACSV(dataList)

            // Guardar el CSV en el archivo
            guardarCSVEnDescargas(csvData, this)
        }

    }
    fun convertirTablaACSV(dataList: List<dataModel>): String {
        val stringBuilder = StringBuilder()

        // Agregar cabecera si es necesario
        stringBuilder.append("Folio,Municipio,Localidad,Primer Apellido,Segundo Apellido,Nombres,Fecha de Nacimiento,Sexo,Estatura,Peso,Ultima Fecha de Medida, Ultimo Perimetro de Brazo,Dx MUAC actual,Inseguridad Alimentaria Actual,Nombre de Padre o Madre,Nombre COC,Padrino,Ultima Fecha Paquetes,Cantidad de Paquetes,Dosis Diaria\n")

        // Agregar datos
        dataList.forEach { item ->
            // Suponiendo que item es una tupla o un objeto con valores
            stringBuilder.append("${item.value1},${item.value2},${item.value3},${item.value4},${item.value5},${item.value6},${item.value7},${item.value8},${item.value9},${item.value10},${item.value11},${item.value12},${item.value13},${item.value14},${item.value15},${item.value16},${item.value17},${item.value18},${item.value19},${item.value20}\n")
        }

        return stringBuilder.toString()
    }

    fun guardarCSVEnDescargas(csvData: String, context: Context) {
        val fileName = "datosNinoSano.csv"

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Buscar si el archivo ya existe en el MediaStore
                val selection = "${MediaStore.MediaColumns.DISPLAY_NAME}=?"
                val selectionArgs = arrayOf(fileName)
                val queryUri = MediaStore.Files.getContentUri("external")

                context.contentResolver.query(
                    queryUri,
                    arrayOf(MediaStore.MediaColumns._ID),
                    selection,
                    selectionArgs,
                    null
                )?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        // Si el archivo ya existe, obtén su Uri y sobrescríbelo
                        val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                        val documentUri = ContentUris.withAppendedId(queryUri, id)

                        context.contentResolver.openOutputStream(documentUri, "wt").use { outputStream ->
                            outputStream ?: throw IOException("No se pudo abrir el archivo CSV para escribir")
                            outputStream.write(csvData.toByteArray())
                        }
                    } else {
                        // Si el archivo no existe, crea uno nuevo
                        val values = ContentValues().apply {
                            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                            put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                        }

                        val uri = context.contentResolver.insert(
                            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                            values
                        ) ?: throw IOException("No se pudo crear el archivo CSV en Descargas")

                        context.contentResolver.openOutputStream(uri).use { outputStream ->
                            outputStream ?: throw IOException("No se pudo abrir el archivo CSV para escribir")
                            outputStream.write(csvData.toByteArray())
                        }
                    }
                }

                Toast.makeText(context, "Archivo CSV guardado en Descargas", Toast.LENGTH_LONG).show()
            } else {
                // Para versiones anteriores a Android 10
                val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(directory, fileName)

                file.writeText(csvData)

                Toast.makeText(context, "Archivo CSV guardado en ${file.absolutePath}", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error al guardar el archivo CSV", Toast.LENGTH_LONG).show()
        }
    }

}