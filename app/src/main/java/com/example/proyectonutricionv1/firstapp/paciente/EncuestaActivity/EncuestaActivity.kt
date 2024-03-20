package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.example.proyectonutricionv1.R
import android.view.View

class EncuestaActivity : AppCompatActivity() {
    private var mpList = mutableListOf<MediaPlayer?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        var clasificacion: String?
        val value1 = intent.getStringExtra("Municipio")
        val value2 = intent.getStringExtra("Localidad")
        val value3 = intent.getStringExtra("PrimerApellido")
        val value4 = intent.getStringExtra("SegundoApellido")
        val value5 = intent.getStringExtra("Nombres")
        val value6 = intent.getStringExtra("FechaNacimiento")
        val value7 = intent.getStringExtra("Perimetro")
        val value8 = intent.getStringExtra("Sexo")
        val value9 = intent.getStringExtra("COC")

        //AUDIOS
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))
        mpList.add(MediaPlayer.create(this, R.raw.no_manches))


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
                intentNext.putExtra("Municipio", value1)
                intentNext.putExtra("Localidad", value2)
                intentNext.putExtra("PrimerApellido", value3)
                intentNext.putExtra("SegundoApellido", value4)
                intentNext.putExtra("Nombres", value5)
                intentNext.putExtra("FechaNacimiento", value6)
                intentNext.putExtra("Perimetro", value7)
                intentNext.putExtra("Sexo", value8)
                intentNext.putExtra("COC", value9)
                startActivity(intentNext)
            }
        }
    }

    private fun encontrarUltimoSi(respuestas: List<Int>): Int {
        for (i in respuestas.indices.reversed()) {
            if (respuestas[i] == 1) {
                return i
            }
        }
        return -1
    }

    //FUNCIONES REPRODUCCIÓN DE AUDIO
    fun reproducirMediaPlayer(view: View){
        // Obtener el ID del botón presionado
        val buttonId = view.id

        // Determinar qué audio reproducir según el ID del botón
        val audioIndex = when(buttonId) {
            R.id.button_Q1 -> 0
            R.id.button_Q2 -> 1
            R.id.button_Q3 -> 2
            R.id.button_Q4 -> 3
            R.id.button_Q5 -> 4
            R.id.button_Q6 -> 5
            R.id.button_Q7 -> 6
            R.id.button_Q8 -> 7
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