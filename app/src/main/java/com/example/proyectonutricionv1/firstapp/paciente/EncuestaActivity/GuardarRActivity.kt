package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.proyectonutricionv1.R

class GuardarRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardar_ractivity)

        val textViewClasificacion = findViewById<TextView>(R.id.Respuesta_inseguridad)
        val textViewLocalidad = findViewById<TextView>(R.id.Respuesta_localidad)
        val textViewNombre = findViewById<TextView>(R.id.Respuesta_nombre)
        val textViewBrazo = findViewById<TextView>(R.id.Respuesta_diametro)

        val clasificacion = intent.getStringExtra("clasificacion")
        val coc = intent.getStringExtra("COC")
        val paciente = intent.getStringExtra("Paciente")
        val nacimiento = intent.getStringExtra("Nacimiento")
        val brazo = intent.getStringExtra("Brazo")
        val localidad = intent.getStringExtra("Localidad")

        textViewClasificacion.text = clasificacion
        textViewLocalidad.text = localidad
        textViewNombre.text = paciente
        textViewBrazo.text = brazo
    }
}