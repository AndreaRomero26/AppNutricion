package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.example.proyectonutricionv1.firstapp.MainMenu

class RegistrarP2Activity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var btnInstruccionesRP2: Button
    private lateinit var btnSobresRP2: Button
    private lateinit var btnDosisRP2: Button
    private lateinit var btnCalcularNDx: Button

    private var mpList = mutableListOf<MediaPlayer?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_p2)

        dbHelper = DBHelper(this)

        btnInstruccionesRP2=findViewById(R.id.btnInstruccionesRP2)
        btnSobresRP2 = findViewById<Button>(R.id.btnSobresRP2)
        btnDosisRP2 = findViewById<Button>(R.id.btnDosisRP2)
        btnCalcularNDx = findViewById<Button>(R.id.btnCalcularNDx)

        val textViewNombre = findViewById<TextView>(R.id.rNombreRP2)
        val textViewFolio = findViewById<TextView>(R.id.rFolioRP2)
        val textViewBrazo = findViewById<TextView>(R.id.rBrazoAnterior)
        val textViewDx = findViewById<TextView>(R.id.rDxAnterior)
        val textViewNewDx = findViewById<TextView>(R.id.rDxNuevo)
        val textViewInseguridad = findViewById<TextView>(R.id.rSeguridadAlimAnterior)
        val editTextBrazo = findViewById<TextView>(R.id.rBrazoNuevo)
        val btnGuardarRegistro = findViewById<Button>(R.id.btnGuardarRegistro)

        val nombre = intent.getStringExtra("Nombre")!!
        val folio = intent.getStringExtra("Folio")!!
        textViewNombre.text = nombre.uppercase()
        textViewFolio.text = folio

        val dataRP = dbHelper.getNuevoRegistro(folio)
        val brazoAnterior: Double = dataRP.first().first  // Primer valor del primer par
        val dxAnterior: String = dataRP.first().second // Segundo valor del primer par
        val inseguridadAnterior: String = dataRP.first().third // Tercer valor del primer par

        textViewBrazo.text = brazoAnterior.toString()
        textViewDx.text = dxAnterior.uppercase()
        textViewInseguridad.text = inseguridadAnterior.uppercase()

        val dataDosis = dbHelper.getDosis(folio)
        btnSobresRP2.text= "${dataDosis.first().second} Sobres"
        btnDosisRP2.text= "${dataDosis.first().third} Al dia"

        var muacNuevo= ""
        btnCalcularNDx.setOnClickListener {
            val nuevoBrazoD=editTextBrazo.text.toString().toDoubleOrNull()
            if (nuevoBrazoD != null) {
                // El valor se convirtió correctamente a Double
                // Puedes utilizar value12 como un número decimal
                if (nuevoBrazoD<=11.5){
                    muacNuevo="Desnutricion grave"
                }
                else if (nuevoBrazoD>11.5 && nuevoBrazoD<=12.5){
                    muacNuevo="Desnutricion moderada"
                }
                else if (nuevoBrazoD>12.5 && nuevoBrazoD<=13.5){
                    muacNuevo="Riesgo de desnutricion"
                }
                else if (nuevoBrazoD>13.5){
                    muacNuevo="Sin desnutricion"
                }
            } else {
                // El valor no se pudo convertir a Double
                muacNuevo="Error"
            }
            textViewNewDx.text=muacNuevo.uppercase()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(btnCalcularNDx.windowToken, 0)
        }

        btnGuardarRegistro.setOnClickListener {
            val nuevoBrazo=editTextBrazo.text.toString().toDouble()
            dbHelper.insertRegistro(folio, nuevoBrazo, muacNuevo)
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

        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))

    }
    fun reproducirMediaPlayer(view: View){
        // Obtener el ID del botón presionado
        val buttonId = view.id

        // Determinar qué audio reproducir según el ID del botón
        val audioIndex = when(buttonId) {
            R.id.btnInstruccionesRP2 -> 0
            R.id.btnSobresRP2 -> 1
            R.id.btnDosisRP2 -> 2
            else -> -1
        }

        // Verificar si el índice del audio es válido
        if (audioIndex != -1) {
            val mp = mpList[audioIndex]

            if (mp?.isPlaying == true) {
                mp.pause()
            } else {
                mp?.seekTo(0)
                mp?.start()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Liberar los recursos de todos los MediaPlayers
        mpList.forEach { mp ->
            mp?.release()
        }
    }


}