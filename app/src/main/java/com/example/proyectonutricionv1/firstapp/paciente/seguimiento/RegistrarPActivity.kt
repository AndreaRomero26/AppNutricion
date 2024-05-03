package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import androidx.core.content.ContextCompat


class RegistrarPActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var buttonRP2: Button
    private lateinit var textViewFolioRP: TextView
    private lateinit var textViewNombreRP: TextView
    private var lastSelectedRow: TableRow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_pactivity)

        textViewFolioRP = findViewById(R.id.rFolioRegistrarP)
        textViewNombreRP = findViewById(R.id.rNombreRegistrarP)
        buttonRP2 = findViewById<Button>(R.id.buttonRP2)
        val intentRP2 = Intent(this, RegistrarP2Activity::class.java)

        dbHelper = DBHelper(this)
        val dataList = dbHelper.getAllData()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val textSizeSP = 20f

        for (data in dataList) {
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }

            listOf(data.value1, data.value2, data.value3, data.value4, data.value5, data.value6,
                data.value7, data.value8, data.value9, data.value10, data.value11,
                data.value12.toString(), data.value13, data.value14, data.value15,
                data.value16, data.value17, data.value18, data.value19, data.value20, data.value21)
                .forEach { value ->
                    val textView = TextView(this).apply {
                        text = value
                        setPadding(10, 10, 10, 10)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP)
                    }
                    tableRow.addView(textView)
                }

            tableRow.setOnClickListener {
                // Reset the background color of the previously selected row
                lastSelectedRow?.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))

                // Set the background color of the current row
                tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRowHighlight))

                // Store the current row as the last selected row
                lastSelectedRow = tableRow

                textViewFolioRP.text = data.value1
                textViewNombreRP.text = "${data.value4.uppercase()} ${data.value5.uppercase()} ${data.value6.uppercase()}"
                checkFieldsForEmptyValues()
                intentRP2.putExtra("Nombre", textViewNombreRP.text.toString())
                intentRP2.putExtra("Folio", data.value1)
            }

            tableLayout.addView(tableRow)
        }

        buttonRP2.isEnabled = false // Initially disabled
        buttonRP2.setOnClickListener {
            startActivity(intentRP2)
        }
    }

    private fun checkFieldsForEmptyValues() {
        buttonRP2.isEnabled = textViewFolioRP.text.isNotEmpty() && textViewNombreRP.text.isNotEmpty()
    }
}