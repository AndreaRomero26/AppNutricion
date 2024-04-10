package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

class MostrarP2Activity : AppCompatActivity() {

    private lateinit var textViewFolioMP: TextView
    private lateinit var textViewNombreMP: TextView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_p2)

        textViewFolioMP = findViewById<TextView>(R.id.rFolioRegistrarP)
        textViewNombreMP = findViewById<TextView>(R.id.rNombreRegistrarP)

        val Folio = intent.getStringExtra("Folio")!!
        val Nombre = intent.getStringExtra("Nombre")

        textViewNombreMP.text=Nombre
        textViewFolioMP.text=Folio

        val lineChart = findViewById<LineChart>(R.id.chart)

        dbHelper = DBHelper(this)

        val datos = dbHelper.getValoresPorFolio(Folio)

// Crear las entradas para el gráfico a partir de los datos de la base de datos
        val entries = ArrayList<Entry>()
        for ((index, value) in datos.withIndex()) {
            entries.add(Entry(index.toFloat(), value))
        }

// Crear un dataset y asignarlo al gráfico
        val dataSet = LineDataSet(entries, "Valores del Folio $Folio")
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate() // Refrescar el gráfico

    }
}