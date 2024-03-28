package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper

class MostrarPActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var buttonMP: Button
    private lateinit var textViewFolioMP: TextView
    private lateinit var textViewNombreMP: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_pactivity)

        textViewFolioMP = findViewById<TextView>(R.id.rFolioRegistrarP)
        textViewNombreMP = findViewById<TextView>(R.id.rNombreRegistrarP)
        buttonMP= findViewById<Button>(R.id.buttonRP2)

        val intentMP2 = Intent(this, MostrarP2Activity::class.java)
        dbHelper = DBHelper(this)
        val dataList = dbHelper.getAllData()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val textSizeSP = 20f

        for ((value1, value2, value3,value4, value5, value6, value7, value8, value9, value10, value11, value12, value13,value14, value15, value16, value17, value18, value19, value20) in dataList) {
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
            textView1.setOnClickListener {
                Toast.makeText(this, "Seleccionaste el paciente: $value1", Toast.LENGTH_SHORT).show()
                textViewFolioMP.text= value1
                val nombreRP= "$value4 $value5 $value6".uppercase()
                textViewNombreMP.text=nombreRP
                checkFieldsForEmptyValues()
                intentMP2.putExtra("Nombre", nombreRP)
                intentMP2.putExtra("Folio", value1)
            }
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
            textView12.text = value12
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
        buttonMP.isEnabled = false // Inicialmente desactivado
        buttonMP.setOnClickListener {
            startActivity(intentMP2)
        }

    }

    private fun checkFieldsForEmptyValues() {
        val folioText = textViewFolioMP.text.toString()
        val nombreText = textViewNombreMP.text.toString()

        // Habilitar el botón si los TextViews tienen texto, deshabilitarlo si están vacíos
        buttonMP.isEnabled = folioText.isNotEmpty() && nombreText.isNotEmpty()
    }
}