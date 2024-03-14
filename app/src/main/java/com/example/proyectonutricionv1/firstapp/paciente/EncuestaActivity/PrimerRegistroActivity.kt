package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class PrimerRegistroActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText
    private lateinit var editText9: EditText
    private lateinit var btn_sig_encuesta: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_registro)

        editText1 = findViewById(R.id.editTextNombreProf)
        editText2 = findViewById(R.id.editTextPrimerApellidoPx)
        editText3 = findViewById(R.id.editTextSegundoApellidoPx)
        editText4 = findViewById(R.id.editTextNombresPx)
        editText5 = findViewById(R.id.editTextFechaNac)
        editText6 = findViewById(R.id.editTextPerimetro)
        editText7 = findViewById(R.id.editTextMunicipio)
        editText8 = findViewById(R.id.editTextLocalidad)
        editText9 = findViewById(R.id.editTextSexo)

        val textViewFecha = findViewById<TextView>(R.id.respuesta_fecha)
        btn_sig_encuesta = findViewById<Button>(R.id.btn_sig_encuesta)
        val intent_sig_encuesta = Intent(this, EncuestaActivity::class.java)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        textViewFecha.setText(formattedDate)


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se utiliza en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se utiliza en este caso
            }

            override fun afterTextChanged(s: Editable?) {
                checkEditTexts()
            }
        }

        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)
        editText3.addTextChangedListener(textWatcher)
        editText4.addTextChangedListener(textWatcher)
        editText5.addTextChangedListener(textWatcher)
        editText6.addTextChangedListener(textWatcher)
        editText7.addTextChangedListener(textWatcher)
        editText8.addTextChangedListener(textWatcher)
        editText9.addTextChangedListener(textWatcher)

        // Inicialmente, deshabilitar el botón
        btn_sig_encuesta.isEnabled = false

        btn_sig_encuesta.setOnClickListener {
            val value1 = editText7.text.toString()
            val value2 = editText8.text.toString()
            val value3 = editText2.text.toString()
            val value4 = editText3.text.toString()
            val value5 = editText4.text.toString()
            val value6 = editText5.text.toString()
            val value7 = editText6.text.toString()
            val value8 = editText9.text.toString()
            val value9 = editText1.text.toString()
            intent_sig_encuesta.putExtra("Municipio", value1)
            intent_sig_encuesta.putExtra("Localidad", value2)
            intent_sig_encuesta.putExtra("PrimerApellido", value3)
            intent_sig_encuesta.putExtra("SegundoApellido", value4)
            intent_sig_encuesta.putExtra("Nombres", value5)
            intent_sig_encuesta.putExtra("FechaNacimiento", value6)
            intent_sig_encuesta.putExtra("Perimetro", value7)
            intent_sig_encuesta.putExtra("Sexo", value8)
            intent_sig_encuesta.putExtra("COC", value9)
            startActivity(intent_sig_encuesta)
        }
    }

    private fun checkEditTexts() {
        val nombreProf = editText1.text.toString()
        val primerApellido = editText2.text.toString()
        val segundoApellido = editText3.text.toString()
        val nombres = editText4.text.toString()
        val fechaNac = editText5.text.toString()
        val perimetro = editText6.text.toString()
        val municipio = editText7.text.toString()
        val localidad = editText8.text.toString()
        val sexo = editText9.text.toString()

        // Verificar si todos los EditText tienen texto
        val allFilled = nombreProf.isNotEmpty() &&
                primerApellido.isNotEmpty() &&
                segundoApellido.isNotEmpty() &&
                nombres.isNotEmpty() &&
                fechaNac.isNotEmpty() &&
                perimetro.isNotEmpty() &&
                municipio.isNotEmpty() &&
                localidad.isNotEmpty() &&
                sexo.isNotEmpty()

        // Habilitar o deshabilitar el botón según si todos los EditText tienen texto
        btn_sig_encuesta.isEnabled = allFilled
    }
}