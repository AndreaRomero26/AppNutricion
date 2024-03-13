package com.example.proyectonutricionv1.firstapp.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.PacienteActivity
import com.example.proyectonutricionv1.firstapp.StaffActivity
import com.example.proyectonutricionv1.firstapp.paciente.seguimiento.MostrarPActivity
import com.example.proyectonutricionv1.firstapp.paciente.seguimiento.RegistrarPActivity

class SeguimientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seguimiento)

        val btnRegistrarP = findViewById<Button>(R.id.btnRegistrarP)
        val btnMostrarP = findViewById<Button>(R.id.btnMostrarP)

        // Crear un Intent para cada botón
        val intentRegistrarP = Intent(this, RegistrarPActivity::class.java)
        val intentMostrarP = Intent(this, MostrarPActivity::class.java)

        btnRegistrarP.setOnClickListener {
            startActivity(intentRegistrarP)
        }

        btnMostrarP.setOnClickListener {
            startActivity(intentMostrarP)
        }

    }
}