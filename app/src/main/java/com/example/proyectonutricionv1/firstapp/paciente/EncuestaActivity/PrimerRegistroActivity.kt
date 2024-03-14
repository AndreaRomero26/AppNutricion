package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper


class PrimerRegistroActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText

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


        val btn_sig_encuesta = findViewById<Button>(R.id.btn_sig_encuesta)

        val intent_sig_encuesta = Intent(this, EncuestaActivity::class.java)

        btn_sig_encuesta.setOnClickListener {
            val value1 = editText7.text.toString()
            val value2 = editText8.text.toString()
            val value3 = editText2.text.toString()
            val value4 = editText3.text.toString()
            val value5 = editText4.text.toString()
            val value6 = editText5.text.toString()
            val value7 = editText6.text.toString()
            val value8 = editText1.text.toString()
            intent_sig_encuesta.putExtra("Municipio", value1)
            intent_sig_encuesta.putExtra("Localidad", value2)
            intent_sig_encuesta.putExtra("PrimerApellido", value3)
            intent_sig_encuesta.putExtra("SegundoApellido", value4)
            intent_sig_encuesta.putExtra("Nombres", value5)
            intent_sig_encuesta.putExtra("FechaNacimiento", value6)
            intent_sig_encuesta.putExtra("Perimetro", value7)
            intent_sig_encuesta.putExtra("COC", value8)
            startActivity(intent_sig_encuesta)
        }


    }
}