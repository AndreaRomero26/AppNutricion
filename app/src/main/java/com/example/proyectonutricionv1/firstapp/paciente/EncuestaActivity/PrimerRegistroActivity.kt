package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.app.DatePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.proyectonutricionv1.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class PrimerRegistroActivity : AppCompatActivity() {

    //Declarar sin inicializar editText de los datos del paciente
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var spinnerMunicipio: Spinner
    private lateinit var editText7: EditText
    private lateinit var btnsexo: RadioGroup
    private lateinit var editText8: EditText
    private lateinit var editText9: EditText
    private lateinit var editText10: EditText
    private lateinit var editText11: EditText
    //Declarar sin inicializar boton siguiente
    private lateinit var btn_sig_encuesta: Button
    //Varible para el sexo, se modifica mas adelante
    private var value10: String="Hombre"
    //Declarar arreglo para audios
    private var mpList = mutableListOf<MediaPlayer?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_registro)

        //Identificar cada elemento del xml con la variable del kotlin
        editText1 = findViewById(R.id.editTextNombreProf)
        editText2 = findViewById(R.id.editTextPrimerApellidoPx)
        editText3 = findViewById(R.id.editTextSegundoApellidoPx)
        editText4 = findViewById(R.id.editTextNombresPx)
        editText5 = findViewById(R.id.editTextFechaNac)
        editText6 = findViewById(R.id.editTextPerimetro)
        spinnerMunicipio = findViewById(R.id.spinnerMunicipio)
        editText7 = findViewById(R.id.editTextLocalidad)
        btnsexo = findViewById(R.id.radioGroupSexo)
        editText8 = findViewById(R.id.editTextEstatura)
        editText9 = findViewById(R.id.editTextPeso)
        editText10 = findViewById(R.id.editTextPadre)
        editText11 = findViewById(R.id.editTextPadrino)

        val textViewFecha = findViewById<TextView>(R.id.respuesta_fecha)
        btn_sig_encuesta = findViewById<Button>(R.id.btn_sig_encuesta)
        //Declaracion del intent para pasar a la siguiente ventana
        val intent_sig_encuesta = Intent(this, EncuestaActivity::class.java)

        //Fecha actual en la ventana de primer registro
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        textViewFecha.text = formattedDate

        //Adaptador para poder usar el spinner de municipio
        ArrayAdapter.createFromResource(
            this,
            R.array.municipios_array,
            R.layout.spinner_item // Usa aquí tu layout personalizado
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item) // Usa el mismo layout para el desplegable, o crea otro si quieres diferenciarlo
            spinnerMunicipio.adapter = adapter
        }

        //TextWatcher para detectar que se llenen los campos
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

        //Modificar variable value10 para el sexo segun el boton
        btnsexo.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonHombre -> {
                    value10 = "Hombre"
                }
                R.id.radioButtonMujer -> {
                    value10 = "Mujer"
                }
            }
        }

        //A cada editText obligatorio poner un textWatcher para checar que se llenen los campos
        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)
        editText3.addTextChangedListener(textWatcher)
        editText4.addTextChangedListener(textWatcher)
        editText5.addTextChangedListener(textWatcher)
        editText6.addTextChangedListener(textWatcher)
        editText7.addTextChangedListener(textWatcher)
        editText10.addTextChangedListener(textWatcher)

        //Checar que se haya seleccionado un municipio en el spinner
        spinnerMunicipio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // La vista (view) puede ser null, así que verifica antes de usarla
                if (view != null) {
                    checkEditTexts()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Manejar el caso de que no se haya seleccionado nada
            }
        }

        // Inicialmente, deshabilitar el botón si no estan los campos obligatorios llenos
        btn_sig_encuesta.isEnabled = false

        //AUDIOS
        mpList.add(MediaPlayer.create(this, R.raw.instrucciones_1))
        mpList.add(MediaPlayer.create(this, R.raw.apellido_paterno))
        mpList.add(MediaPlayer.create(this, R.raw.apellido_materno))
        mpList.add(MediaPlayer.create(this, R.raw.nombre))
        mpList.add(MediaPlayer.create(this, R.raw.fecha_nacimiento))
        mpList.add(MediaPlayer.create(this, R.raw.tutor))

        //Pulsar el boton siguiente
        btn_sig_encuesta.setOnClickListener {
            val value1 = spinnerMunicipio.selectedItem.toString()
            val value2 = editText7.text.toString()
            val value3 = editText2.text.toString()
            val value4 = editText3.text.toString()
            val value5 = editText4.text.toString()
            val value6 = editText5.text.toString()
            val value7 = editText8.text.toString()
            val value8 = editText9.text.toString()
            var value9: Double? = null

            try {
                value9 = editText6.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                // Manejar el error si el texto no es un número
                Toast.makeText(this, "Por favor, ingrese un número en perímetro de brazo.", Toast.LENGTH_SHORT).show()
            }
            if (value9 == null) {
                return@setOnClickListener  // No continúa con el proceso si value9 es nulo
            }
            val value11 = editText10.text.toString()
            val value12 = editText1.text.toString()
            val value13 = editText11.text.toString()
            intent_sig_encuesta.putExtra("Municipio", value1)
            intent_sig_encuesta.putExtra("Localidad", value2)
            intent_sig_encuesta.putExtra("PrimerApellido", value3)
            intent_sig_encuesta.putExtra("SegundoApellido", value4)
            intent_sig_encuesta.putExtra("Nombres", value5)
            intent_sig_encuesta.putExtra("FechaNacimiento", value6)
            intent_sig_encuesta.putExtra("Estatura", value7)
            intent_sig_encuesta.putExtra("Peso", value8)
            intent_sig_encuesta.putExtra("Perimetro", value9)
            intent_sig_encuesta.putExtra("Sexo", value10)
            intent_sig_encuesta.putExtra("Tutor", value11)
            intent_sig_encuesta.putExtra("COC", value12)
            intent_sig_encuesta.putExtra("Padrino", value13)
            startActivity(intent_sig_encuesta)
        }
        // Manejar clic para abrir el DatePickerDialog
        editText5.setOnClickListener {
            showDatePickerDialog()
        }

        // Manejar el foco para abrir el DatePickerDialog cuando se navega con el botón "Siguiente"
        editText5.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) showDatePickerDialog()
        }
    }

    //Metodo para abrir el calendario en fecha de nacimiento
    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
            // Establecer la fecha en el EditText en el formato deseado
            val formattedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
            editText5.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
        editText5.clearFocus()  // Para evitar múltiples aperturas del diálogo
    }

    //Metodo para checar que esten llenos los campos obligatorios, en cada textWatcher y listener
    private fun checkEditTexts() {
        val nombreProf = editText1.text.toString()
        val primerApellido = editText2.text.toString()
        val segundoApellido = editText3.text.toString()
        val nombres = editText4.text.toString()
        val fechaNac = editText5.text.toString()
        val perimetro = editText6.text.toString()
        val municipio = spinnerMunicipio.selectedItemPosition > 0 // Asume que la posición 0 es el prompt "Seleccione un Municipio"
        val localidad = editText7.text.toString()
        val padre = editText10.text.toString()


        // Verificar si todos los campos (EditTexts y el Spinner) tienen valores
        val allFilled = nombreProf.isNotEmpty() && primerApellido.isNotEmpty() && segundoApellido.isNotEmpty() && nombres.isNotEmpty() && fechaNac.isNotEmpty() && perimetro.isNotEmpty() && municipio && localidad.isNotEmpty()&& padre.isNotEmpty()

        // Habilitar o deshabilitar el botón según si todos los campos tienen valores
        btn_sig_encuesta.isEnabled = allFilled
    }

    //FUNCIONES REPRODUCCIÓN DE AUDIO
    fun reproducirMediaPlayer(view: View){
        // Obtener el ID del botón presionado
        val buttonId = view.id

        // Determinar qué audio reproducir según el ID del botón
        val audioIndex = when(buttonId) {
            R.id.buttonI -> 0
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            R.id.button4 -> 4
            R.id.button5 -> 5
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