package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper

class RegistrarP2Activity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_p2)

        dbHelper = DBHelper(this)

        val textViewNombre = findViewById<TextView>(R.id.rNombreRP2)
        val textViewFolio = findViewById<TextView>(R.id.rFolioRP2)
        val textViewBrazo = findViewById<TextView>(R.id.rBrazoAnterior)
        val textViewDx = findViewById<TextView>(R.id.rDxAnterior)
        val textViewInseguridad = findViewById<TextView>(R.id.rSeguridadAlimAnterior)

        val nombre = intent.getStringExtra("Nombre")
        val folio = intent.getStringExtra("Folio")!!
        textViewNombre.text = nombre
        textViewFolio.text = folio

        val dataRP = dbHelper.getNuevoRegistro(folio)
        val brazoAnterior: String = dataRP.first().first  // Primer valor del primer par
        val dxAnterior: String = dataRP.first().second // Segundo valor del primer par
        val inseguridadAnterior: String = dataRP.first().third // Tercer valor del primer par

        textViewBrazo.text = brazoAnterior
        textViewDx.text = dxAnterior
        textViewInseguridad.text = inseguridadAnterior

    }
}