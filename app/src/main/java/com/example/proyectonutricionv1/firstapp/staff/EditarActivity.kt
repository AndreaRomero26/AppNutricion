package com.example.proyectonutricionv1.firstapp.staff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import androidx.core.content.ContextCompat


class EditarActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var buttonDeletePx: Button
    private lateinit var buttonEditarPx: Button
    private lateinit var textViewFolioEE: TextView
    private lateinit var textViewNombreEE: TextView
    private var lastSelectedRow: TableRow? = null
    private var idRegistroAEliminar: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        textViewFolioEE = findViewById(R.id.rFolioRegistrarP)
        textViewNombreEE = findViewById(R.id.rNombreRegistrarP)
        buttonDeletePx= findViewById(R.id.buttonDeletePx)
        buttonEditarPx= findViewById(R.id.buttonEditarPx)

        val intentEE = Intent(this, Editar2Activity::class.java)
        dbHelper = DBHelper(this)
        val dataList = dbHelper.getAllData()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val textSizeSP = 20f

        dataList.forEach { data ->
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
                tag = data.value1 // Tag each row with the identifier (value1)
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }

            listOf(data.value1, data.value2, data.value3, data.value4, data.value5, data.value6,
                data.value7, data.value8, data.value9, data.value10, data.value11,
                data.value12.toString(), data.value13, data.value14, data.value15,
                data.value16, data.value17, data.value18, data.value19, data.value20).forEach { value ->
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

                textViewFolioEE.text = data.value1
                textViewNombreEE.text = "${data.value4} ${data.value5} ${data.value6}".uppercase()
                idRegistroAEliminar = data.value1
                checkFieldsForEmptyValues()
                intentEE.putExtra("Nombre", textViewNombreEE.text.toString())
                intentEE.putExtra("Folio", idRegistroAEliminar)
            }

            tableLayout.addView(tableRow)
        }

        buttonDeletePx.isEnabled = false // Initially disabled
        buttonEditarPx.isEnabled = false // Initially disabled
        buttonDeletePx.setOnClickListener {
            // Confirmation dialog for deletion
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmar eliminación")
            builder.setMessage("¿Estás seguro de querer eliminar este registro?")

            // Set action for the "Yes" button
            builder.setPositiveButton("Sí") { dialog, which ->
                dbHelper.eliminarRegistro(idRegistroAEliminar)
                // Remove the corresponding row in TableLayout
                val rowToDelete = tableLayout.findViewWithTag<TableRow>(idRegistroAEliminar)
                tableLayout.removeView(rowToDelete)
                // Reset and update UI
                idRegistroAEliminar = ""
                textViewFolioEE.text = ""
                textViewNombreEE.text = ""
                checkFieldsForEmptyValues()
            }

            // Set action for the "No" button
            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            // Show the AlertDialog
            builder.create().show()
        }

        buttonEditarPx.setOnClickListener {
            intentEE.putExtra("Folio", idRegistroAEliminar)
            startActivity(intentEE)
        }
    }

    private fun checkFieldsForEmptyValues() {
        val folioText = textViewFolioEE.text.toString()
        val nombreText = textViewNombreEE.text.toString()

        // Enable buttons if the TextViews have text, disable them if empty
        buttonDeletePx.isEnabled = folioText.isNotEmpty() && nombreText.isNotEmpty()
        buttonEditarPx.isEnabled = folioText.isNotEmpty() && nombreText.isNotEmpty()
    }
}
