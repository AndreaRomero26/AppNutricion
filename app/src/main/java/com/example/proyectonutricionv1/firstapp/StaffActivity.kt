package com.example.proyectonutricionv1.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.staff.EditarActivity
import com.example.proyectonutricionv1.firstapp.staff.ExpedientesActivity

class StaffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        val btnExpedientes = findViewById<Button>(R.id.btnExpedientes)
        val btnEditar = findViewById<Button>(R.id.btnEditar)

        val intentExpedientes = Intent(this,  ExpedientesActivity::class.java)
        val intentEditar = Intent(this, EditarActivity::class.java)


        btnExpedientes.setOnClickListener {
            startActivity(intentExpedientes)
        }

        btnEditar.setOnClickListener {
            startActivity(intentEditar)
        }
    }
}