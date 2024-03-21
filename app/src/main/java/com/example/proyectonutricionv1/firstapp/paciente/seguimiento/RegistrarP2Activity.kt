package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.example.proyectonutricionv1.firstapp.MainMenu

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

        val nombre = intent.getStringExtra("Nombre")!!
        val folio = intent.getStringExtra("Folio")!!
        textViewNombre.text = nombre.uppercase()
        textViewFolio.text = folio

        val dataRP = dbHelper.getNuevoRegistro(folio)
        val brazoAnterior: String = dataRP.first().first  // Primer valor del primer par
        val dxAnterior: String = dataRP.first().second // Segundo valor del primer par
        val inseguridadAnterior: String = dataRP.first().third // Tercer valor del primer par

        textViewBrazo.text = brazoAnterior
        textViewDx.text = dxAnterior.uppercase()
        textViewInseguridad.text = inseguridadAnterior

        val btnGuardarRegistro = findViewById<Button>(R.id.btnGuardarRegistro)


        btnGuardarRegistro.setOnClickListener {
            // Mostrar mensaje de éxito con AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¡Éxito!")
            builder.setMessage("Expediente guardado con éxito.")
            builder.setPositiveButton("OK") { dialog, which ->
                // Regresar a la actividad principal (opcional)
                val intentMainMenu = Intent(this, MainMenu::class.java)
                intentMainMenu.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intentMainMenu)
            }
            builder.show()
        }

    }
}