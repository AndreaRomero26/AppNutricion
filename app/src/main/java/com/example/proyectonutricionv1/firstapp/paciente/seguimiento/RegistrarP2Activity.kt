package com.example.proyectonutricionv1.firstapp.paciente.seguimiento

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var editTextComentario: EditText

    // MediaPlayers para cada botón
    private var mpInstrucciones: MediaPlayer? = null
    private var mpSobresRP2: MediaPlayer? = null
    private var mpDosisRP2: MediaPlayer? = null
    private var mpNoMasDosis: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_p2)

        dbHelper = DBHelper(this)

        btnInstruccionesRP2=findViewById(R.id.btnInstruccionesRP2)
        btnSobresRP2 = findViewById<Button>(R.id.btnSobresRP2)
        btnDosisRP2 = findViewById<Button>(R.id.btnDosisRP2)
        btnCalcularNDx = findViewById<Button>(R.id.btnCalcularNDx)
        editTextComentario = findViewById(R.id.comentario)

        val textViewNombre = findViewById<TextView>(R.id.rNombreRP2)
        val textViewFolio = findViewById<TextView>(R.id.rFolioRP2)
        val textViewBrazo = findViewById<TextView>(R.id.rBrazoAnterior)
        val textViewDx = findViewById<TextView>(R.id.rDxAnterior)
        val textViewNewDx = findViewById<TextView>(R.id.rDxNuevo)
        val textViewInseguridad = findViewById<TextView>(R.id.rSeguridadAlimAnterior)
        val textViewNewDosis = findViewById<TextView>(R.id.rFechaUltimaDosis)
        val editTextBrazo = findViewById<TextView>(R.id.rBrazoNuevo)
        val btnGuardarRegistro = findViewById<Button>(R.id.btnGuardarRegistro)
        val btnNuevaDosis = findViewById<Button>(R.id.btnNuevaDosis)
        val btnNoMasDosis = findViewById<Button>(R.id.btnNoMasDosis)

        val nombre = intent.getStringExtra("Nombre")!!
        val folio = intent.getStringExtra("Folio")!!
        textViewNombre.text = nombre.uppercase()
        textViewFolio.text = folio

        val dataRP = dbHelper.getDataToNuevoRegistro(folio)
        val brazoAnterior: Double = dataRP.first().first  // Primer valor del primer par
        val dxAnterior: String = dataRP.first().second // Segundo valor del primer par
        val inseguridadAnterior: String = dataRP.first().third // Tercer valor del primer par

        textViewBrazo.text = brazoAnterior.toString()
        textViewDx.text = dxAnterior.uppercase()
        textViewInseguridad.text = inseguridadAnterior.uppercase()

        val dataDosis = dbHelper.getDosis(folio)
        textViewNewDosis.text=dataDosis.first().first
        btnSobresRP2.text= "${dataDosis.first().second} Sobres"
        btnDosisRP2.text= "${dataDosis.first().third} Al día"

        var numPaquetes=dataDosis.first().second
        var dosis=dataDosis.first().third

        var muacNuevo= ""
        btnCalcularNDx.setOnClickListener {
            val nuevoBrazoD = editTextBrazo.text.toString().toDoubleOrNull()
            if (nuevoBrazoD != null) {
                // El valor se convirtió correctamente a Double
                muacNuevo = when {
                    nuevoBrazoD <= 11.5 -> "Desnutrición grave"
                    nuevoBrazoD <= 12.5 -> "Desnutrición moderada"
                    nuevoBrazoD <= 13.5 -> "Riesgo de desnutrición"
                    else -> "Sin desnutrición"
                }
            } else {
                // El valor no se pudo convertir a Double, mostrar mensaje de error
                Toast.makeText(this, "Por favor, inserte un número válido para el perímetro del brazo.", Toast.LENGTH_LONG).show()
                muacNuevo = "Error"
            }
            textViewNewDx.text = muacNuevo.uppercase()

            // Ocultar el teclado después de hacer clic en el botón
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(btnCalcularNDx.windowToken, 0)
        }

        btnGuardarRegistro.setOnClickListener {
            val nuevoBrazo = editTextBrazo.text.toString().toDouble()
            val comentario=editTextComentario.text.toString()
            val newRegistroSuccess = dbHelper.insertRegistro(folio, nuevoBrazo, muacNuevo, comentario)

            // Supongamos que updateDosis es una función que también retorna un booleano
            val updateDosisSuccess = dbHelper.updateDosis(folio, numPaquetes, dosis) // Asegúrate de proporcionar los parámetros correctos

            if (newRegistroSuccess && updateDosisSuccess) {
                // Mostrar mensaje de éxito solo si la inserción y la actualización fueron exitosas
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¡Éxito!")
                builder.setMessage("Progreso y dosis registrados con éxito.")
                builder.setPositiveButton("OK") { dialog, which ->
                    // Regresar a la actividad principal (opcional)
                    val intentMainMenu = Intent(this, MainMenu::class.java)
                    intentMainMenu.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intentMainMenu)
                }
                builder.show()
            } else {
                // Manejar el caso de inserción o actualización fallida
                val errorBuilder = AlertDialog.Builder(this)
                errorBuilder.setTitle("Error")
                errorBuilder.setMessage("No se pudo completar la operación. Intente nuevamente.")
                errorBuilder.setPositiveButton("OK", null)
                errorBuilder.show()
            }
        }


        btnNuevaDosis.setOnClickListener {
            val opcionesTipo1 = arrayOf("ATLC", "SNBL")
            val opcionesTipo2 = arrayOf("1", "2")

            // Crear y mostrar el AlertDialog para la primera selección
            AlertDialog.Builder(this)
                .setTitle("Selecciona la nueva cantidad de paquetes a dejar")
                .setItems(opcionesTipo1) { dialog, which ->
                    // `which` es el índice de la opción seleccionada
                    numPaquetes = opcionesTipo1[which]
                    btnSobresRP2.text="${numPaquetes}"
                    // Puedes guardar la selección en una variable o usarla directamente

                    // Ahora mostrar el segundo AlertDialog para la segunda selección
                    AlertDialog.Builder(this)
                        .setTitle("Selecciona la dosis diaria")
                        .setItems(opcionesTipo2) { dialog2, which2 ->
                            dosis = opcionesTipo2[which2]
                            btnDosisRP2.text="${dosis} Al día"
                            // Puedes guardar la selección en otra variable o usarla directamente

                        }
                        .show()
                }
                .show()
        }

        mpInstrucciones = MediaPlayer.create(this, R.raw.instrucciones_formula)
        mpNoMasDosis = MediaPlayer.create(this, R.raw.no_mas_paquetes)

        btnInstruccionesRP2.setOnClickListener {
            toggleMediaPlayer(mpInstrucciones)
        }

        btnNoMasDosis.setOnClickListener {
            toggleMediaPlayer(mpNoMasDosis)
            numPaquetes="ALTA"
            dosis="ALTA"
            btnSobresRP2.text="${numPaquetes}"
            btnDosisRP2.text="${dosis}"
        }

        btnSobresRP2.setOnClickListener {
            // Selecciona el audio basado en el valor de numPaquetes
            val audioResId = when (numPaquetes) {
                "30" -> R.raw.treinta_paquetes
                "60" -> R.raw.sesenta_paquetes
                "90" -> R.raw.noventa_paquetes
                else -> null
            }
            audioResId?.let {
                if (mpSobresRP2 == null || !mpSobresRP2!!.isPlaying) {
                    mpSobresRP2 = MediaPlayer.create(this, it).apply {
                        start()
                        setOnCompletionListener { mp ->
                            mp.pause()
                            mp.seekTo(0)
                        }
                    }
                } else {
                    toggleMediaPlayer(mpSobresRP2)
                }
            }
        }

        btnDosisRP2.setOnClickListener {
            // Selecciona el audio basado en el valor de dosis
            val audioResId = when (dosis) {
                "1" -> R.raw.unopordia
                "2" -> R.raw.dospordia
                else -> null
            }
            audioResId?.let {
                if (mpDosisRP2 == null || !mpDosisRP2!!.isPlaying) {
                    mpDosisRP2 = MediaPlayer.create(this, it).apply {
                        start()
                        setOnCompletionListener { mp ->
                            mp.pause()
                            mp.seekTo(0)
                        }
                    }
                } else {
                    toggleMediaPlayer(mpDosisRP2)
                }
            }
        }


    }

    private fun toggleMediaPlayer(mediaPlayer: MediaPlayer?) {
        mediaPlayer?.let { mp ->
            if (mp.isPlaying) {
                mp.pause()
            } else {
                mp.seekTo(0)
                mp.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Libera los MediaPlayers al destruir la actividad
        mpInstrucciones?.release()
        mpNoMasDosis?.release()
        mpDosisRP2?.release()
        mpSobresRP2?.release()

        // Nulifica las referencias para evitar fugas de memoria
        mpInstrucciones = null
        mpNoMasDosis = null
        mpDosisRP2 = null
        mpSobresRP2 = null
    }

}