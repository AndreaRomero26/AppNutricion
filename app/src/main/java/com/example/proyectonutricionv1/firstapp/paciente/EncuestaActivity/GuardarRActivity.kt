package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper

class GuardarRActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardar_ractivity)

        dbHelper = DBHelper(this)

        val btnGenerarDB = findViewById<Button>(R.id.btn_generarDB)

        val textViewClasificacion = findViewById<TextView>(R.id.Respuesta_inseguridad)
        val textViewLocalidad = findViewById<TextView>(R.id.Respuesta_localidad)
        val textViewNombre = findViewById<TextView>(R.id.Respuesta_nombre)
        val textViewBrazo = findViewById<TextView>(R.id.Respuesta_diametro)

        val value1 = "555"
        val value2 = intent.getStringExtra("Municipio")!!
        val value3 = intent.getStringExtra("Localidad")!!
        val value4 = intent.getStringExtra("PrimerApellido")!!
        val value5 = intent.getStringExtra("SegundoApellido")!!
        val value6 = intent.getStringExtra("Nombres")!!
        val value7 = intent.getStringExtra("FechaNacimiento")!!
        val value9 = intent.getStringExtra("Perimetro")!!
        val value10 = intent.getStringExtra("COC")!!
        val value11="Desnutrición grave"
        val value12 = intent.getStringExtra("clasificacion")!!

        val nombreCompleto = "$value4 $value5 $value6"
        val ubicacionCompleta = "$value2 $value3"

        textViewClasificacion.text = value11
        textViewLocalidad.text = ubicacionCompleta
        textViewNombre.text = nombreCompleto
        textViewBrazo.text = value7

        btnGenerarDB.setOnClickListener {
            dbHelper.insertData(value1, value2, value3, value4, value5, value6, value7, value9, value10, value11, value12)
        }

    }
}