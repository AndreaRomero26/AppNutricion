package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper


class PrimerRegistroActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_registro)

        editText1 = findViewById(R.id.editTextNombreProf)
        editText2 = findViewById(R.id.editTextPrimerApellidoPx)
        editText3 = findViewById(R.id.editTextFechaNac)
        editText4 = findViewById(R.id.editTextDiametro)
        editText5 = findViewById(R.id.editTextLocalidad)
        dbHelper = DBHelper(this)

        val btn_sig_encuesta = findViewById<Button>(R.id.btn_sig_encuesta)

        val intent_sig_encuesta = Intent(this, EncuestaActivity::class.java)

        btn_sig_encuesta.setOnClickListener {
            val value1 = editText5.text.toString()
            val value2 = editText2.text.toString()
            val value3 = editText3.text.toString()
            val value4 = editText4.text.toString()
            val value5 = editText1.text.toString()
            dbHelper.insertData(value1, value2, value3, value4, value5)
            intent_sig_encuesta.putExtra("COC", value5)
            intent_sig_encuesta.putExtra("Paciente", value2)
            intent_sig_encuesta.putExtra("Nacimiento", value3)
            intent_sig_encuesta.putExtra("Brazo", value4)
            intent_sig_encuesta.putExtra("Localidad", value1)
            startActivity(intent_sig_encuesta)
        }


    }
}