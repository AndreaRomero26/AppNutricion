package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.StaffActivity

class EncuestaActivity : AppCompatActivity() {
    private var clasificacion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        var clasificacion = intent.getStringExtra("clasificacion")
        val value1 = intent.getStringExtra("COC")
        val value2 = intent.getStringExtra("Paciente")
        val value3 = intent.getStringExtra("Nacimiento")
        val value4 = intent.getStringExtra("Brazo")
        val value5 = intent.getStringExtra("Localidad")



        // BOTONES DE RESPUESTA ENCUESTA
        val button_next = findViewById<Button>(R.id.button_next)
        val intentNext = Intent(this, GuardarRActivity::class.java)

        val radioGroupQ1 = findViewById<RadioGroup>(R.id.RadioGroup_Q1)
        val radioGroupQ2 = findViewById<RadioGroup>(R.id.RadioGroup_Q2)
        val radioGroupQ3 = findViewById<RadioGroup>(R.id.RadioGroup_Q3)
        val radioGroupQ4 = findViewById<RadioGroup>(R.id.RadioGroup_Q4)
        val radioGroupQ5 = findViewById<RadioGroup>(R.id.RadioGroup_Q5)
        val radioGroupQ6 = findViewById<RadioGroup>(R.id.RadioGroup_Q6)
        val radioGroupQ7 = findViewById<RadioGroup>(R.id.RadioGroup_Q7)
        val radioGroupQ8 = findViewById<RadioGroup>(R.id.RadioGroup_Q8)

        button_next.setOnClickListener {
            val respuestas = mutableListOf<Int>()
            respuestas.add(if (radioGroupQ1.checkedRadioButtonId == R.id.Q1_si) 1 else if (radioGroupQ1.checkedRadioButtonId == R.id.Q1_no) 0 else 3)
            respuestas.add(if (radioGroupQ2.checkedRadioButtonId == R.id.Q2_si) 1 else if (radioGroupQ2.checkedRadioButtonId == R.id.Q2_no) 0 else 3)
            respuestas.add(if (radioGroupQ3.checkedRadioButtonId == R.id.Q3_si) 1 else if (radioGroupQ3.checkedRadioButtonId == R.id.Q3_no) 0 else 3)
            respuestas.add(if (radioGroupQ4.checkedRadioButtonId == R.id.Q4_si) 1 else if (radioGroupQ4.checkedRadioButtonId == R.id.Q4_no) 0 else 3)
            respuestas.add(if (radioGroupQ5.checkedRadioButtonId == R.id.Q5_si) 1 else if (radioGroupQ5.checkedRadioButtonId == R.id.Q5_no) 0 else 3)
            respuestas.add(if (radioGroupQ6.checkedRadioButtonId == R.id.Q6_si) 1 else if (radioGroupQ6.checkedRadioButtonId == R.id.Q6_no) 0 else 3)
            respuestas.add(if (radioGroupQ7.checkedRadioButtonId == R.id.Q7_si) 1 else if (radioGroupQ7.checkedRadioButtonId == R.id.Q7_no) 0 else 3)
            respuestas.add(if (radioGroupQ8.checkedRadioButtonId == R.id.Q8_si) 1 else if (radioGroupQ8.checkedRadioButtonId == R.id.Q8_no) 0 else 3)

            val ultimoSi = encontrarUltimoSi(respuestas)

            clasificacion = when (ultimoSi) {
                in (respuestas.size - 2) until respuestas.size -> {
                    "INSEGURIDAD ALIMENTARIA GRAVE"
                }
                in 2..5 -> {
                    "INSEGURIDAD ALIMENTARIA MODERADA"
                }
                0, 1 -> {
                    "INSEGURIDAD ALIMENTARIA LEVE"
                }
                else -> "SEGURIDAD ALIMENTARIA"
            }

            if (radioGroupQ1.checkedRadioButtonId == -1 || radioGroupQ2.checkedRadioButtonId == -1 || radioGroupQ3.checkedRadioButtonId == -1
                || radioGroupQ4.checkedRadioButtonId == -1 || radioGroupQ5.checkedRadioButtonId == -1 || radioGroupQ6.checkedRadioButtonId == -1
                || radioGroupQ7.checkedRadioButtonId == -1 || radioGroupQ8.checkedRadioButtonId == -1) {
                Toast.makeText(this,"Por favor, seleccione una respuesta para todas las preguntas.",Toast.LENGTH_SHORT).show()
            }
            else {
                intentNext.putExtra("clasificacion", clasificacion)
                intentNext.putExtra("COC", value1)
                intentNext.putExtra("Paciente", value2)
                intentNext.putExtra("Nacimiento", value3)
                intentNext.putExtra("Brazo", value4)
                intentNext.putExtra("Localidad", value5)
                startActivity(intentNext)
            }
        }
    }

    fun encontrarUltimoSi(respuestas: List<Int>): Int {
        for (i in respuestas.indices.reversed()) {
            if (respuestas[i] == 1) {
                return i
            }
        }
        return -1
    }
}