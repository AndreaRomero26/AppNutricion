package com.example.proyectonutricionv1.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectonutricionv1.R

class MainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Encontrar los botones por su ID
        val btnStaff = findViewById<Button>(R.id.btnStaff)
        val btnPaciente = findViewById<Button>(R.id.btnPaciente)

        // Crear un Intent para cada botón
        val intentStaff = Intent(this, StaffActivity::class.java)
        val intentPaciente = Intent(this, PacienteActivity::class.java)



        btnPaciente.setOnClickListener {
            startActivity(intentPaciente)
        }

        btnStaff.setOnClickListener {
            startActivity(intentStaff)
        }



    }

}