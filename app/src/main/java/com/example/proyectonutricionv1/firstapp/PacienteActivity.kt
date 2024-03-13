package com.example.proyectonutricionv1.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.paciente.EducacionActivity
import com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity.PrimerRegistroActivity
import com.example.proyectonutricionv1.firstapp.paciente.SeguimientoActivity

class PacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)

        val btnNuevoPaciente = findViewById<Button>(R.id.btnNuevoPaciente)
        val btnSeguimiento = findViewById<Button>(R.id.btnSeguimiento)
        val btnEducacion = findViewById<Button>(R.id.btnEducacion)

        val intentPrimerRegistro = Intent(this,  PrimerRegistroActivity::class.java)
        val intentSeguimiento = Intent(this, SeguimientoActivity::class.java)
        val intentEducacion = Intent(this, EducacionActivity::class.java)

        btnNuevoPaciente.setOnClickListener {
            startActivity(intentPrimerRegistro)
        }

        btnSeguimiento.setOnClickListener {
            startActivity(intentSeguimiento)
        }

        btnEducacion.setOnClickListener {
            startActivity(intentEducacion)
        }

    }
}