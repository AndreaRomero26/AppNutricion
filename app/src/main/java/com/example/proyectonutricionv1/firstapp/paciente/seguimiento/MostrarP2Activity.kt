package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter

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

        val datos = dbHelper.getDataGrafica(Folio)
        val entries = ArrayList<Entry>()
        val fechas = ArrayList<String>()

        datos.forEachIndexed { index, (valor, fecha) ->
            entries.add(Entry(index.toFloat(), valor))
            fechas.add(fecha)
        }

        lineChart.xAxis.valueFormatter = DateAxisValueFormatter(fechas)
        val dataSet = LineDataSet(entries, "Valores del Folio")
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        lineChart.invalidate()

    }
}
class DateAxisValueFormatter(private val dates: List<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val index = value.toInt()
        return if (index >= 0 && index < dates.size) dates[index] else ""
    }
}