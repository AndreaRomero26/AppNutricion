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
import com.github.mikephil.charting.components.XAxis

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
        val datos2=dbHelper.getDataToNuevoRegistro(Folio)
        val brazoAnterior: Float = datos2.first().first.toFloat()

        // Crea la lista de entradas para el gráfico, comenzando con el valor único
        val entries = ArrayList<Entry>().apply {
            // Añade el valor único primero
            add(Entry(0f, brazoAnterior))

            // Añade los valores del folio, continuando la secuencia
            datos.forEachIndexed { index, value ->
                add(Entry((index + 1).toFloat(), value))
            }
        }

// Crea un DataSet con las entradas y asigna al gráfico
        val dataSet = LineDataSet(entries, "Valores del Folio $Folio")
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate() // Refresca el gráfico
        // Desactiva el eje Y derecho
        lineChart.axisRight.isEnabled = false
        // Asegura que las etiquetas del eje X estén en la parte inferior (esto debería ser lo predeterminado)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }
}