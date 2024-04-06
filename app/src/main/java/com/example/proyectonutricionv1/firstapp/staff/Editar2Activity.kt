package com.example.proyectonutricionv1.firstapp.staff

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.example.proyectonutricionv1.firstapp.MainMenu
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Editar2Activity : AppCompatActivity() {

    //Declarar los editText, spinner, botones que seran inicializados mas tarde
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText
    private lateinit var editText9: EditText
    private lateinit var editText10: EditText
    private lateinit var editText11: EditText
    private lateinit var spinnerMunicipio: Spinner
    private lateinit var btnsexo: RadioGroup
    private lateinit var btnUpdate: Button
    //Declarar instancia de DBHelper que se iniciara mas tarde
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar2)

        //Campos de los datos del paciente, declaracion textViews, match con los elementos del xml
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

        //Recuperar el folio de la activity anterior
        val folio = intent.getStringExtra("Folio")!!
        //Instancia de la base de datos
        dbHelper = DBHelper(this)

        //Declaracion textView de le fecha
        val textViewFecha = findViewById<TextView>(R.id.respuesta_fecha)
        btnUpdate = findViewById<Button>(R.id.btn_sig_encuesta)


        //Fecha actual en el textView de fecha
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        textViewFecha.setText(formattedDate)

        //Adaptador para spinner de municipios
        ArrayAdapter.createFromResource(
            this,
            R.array.municipios_array,
            R.layout.spinner_item // Usa aquí tu layout personalizado
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item) // Usa el mismo layout para el desplegable, o crea otro si quieres diferenciarlo
            spinnerMunicipio.adapter = adapter
        }

        //Cargas todos los datos del paciente en los campos
        cargarDatosPaciente(folio)

        //Checar que esten llenos los edittext, escuchar los cambios
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se utiliza en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se utiliza en este caso
            }

            override fun afterTextChanged(s: Editable?) {
                checkEditTexts() //Metodo que checa si todos los datos estan llenos
            }
        }
        //Asociar el textWatcher a cada textview
        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)
        editText3.addTextChangedListener(textWatcher)
        editText4.addTextChangedListener(textWatcher)
        editText5.addTextChangedListener(textWatcher)
        editText6.addTextChangedListener(textWatcher)
        editText7.addTextChangedListener(textWatcher)

        //Checar si el spinner fue seleccionado
        spinnerMunicipio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                checkEditTexts()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Inicialmente, deshabilitar el botón si los campos obligatorios no estan llenos
        btnUpdate.isEnabled = false

        // Manejar clic para abrir el DatePickerDialog
        editText5.setOnClickListener {
            //Abrir el calendario para la fecha de nacimiento
            showDatePickerDialog()
        }

        // Manejar el foco para abrir el DatePickerDialog cuando se navega con el botón "Siguiente"
        editText5.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) showDatePickerDialog()
        }

        btnUpdate.setOnClickListener {
            //Convertir a string los valores de los editText
            val value16 = editText1.text.toString()
            val value4 = editText2.text.toString()
            val value5 = editText3.text.toString()
            val value6 = editText4.text.toString()
            val value7 = editText5.text.toString()
            val value3 = editText7.text.toString()
            val value9 = editText8.text.toString()
            val value10 = editText9.text.toString()
            val value15 = editText10.text.toString()
            val value17 = editText11.text.toString()

            // Recoge el valor actual del Spinner para el municipio
            val value2 = spinnerMunicipio.selectedItem.toString()

            // Determina el sexo seleccionado
            val value8 = when (btnsexo.checkedRadioButtonId) {
                R.id.radioButtonHombre -> "Hombre"
                R.id.radioButtonMujer -> "Mujer"
                else -> "" // o un valor por defecto o manejo de error si es necesario
            }

            // Llama al método update del DBHelper
            dbHelper.update(folio, value2, value3, value4, value5, value6, value7, value8, value9, value10, value15, value16, value17)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¡Éxito!")
            builder.setMessage("Expediente guardado con éxito.")
            builder.setPositiveButton("OK") { dialog, which ->
                // Regresar a la actividad principal
                val intentMainMenu = Intent(this, MainMenu::class.java)
                intentMainMenu.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intentMainMenu)
            }
            builder.show()
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

    //Metodo para checar que los campos esten llenos
    private fun checkEditTexts() {
        val nombreProf = editText1.text.toString()
        val primerApellido = editText2.text.toString()
        val segundoApellido = editText3.text.toString()
        val nombres = editText4.text.toString()
        val fechaNac = editText5.text.toString()
        val perimetro = editText6.text.toString()
        val municipio = spinnerMunicipio.selectedItemPosition > 0 // Asume que la posición 0 es el prompt "Seleccione un Municipio"
        val localidad = editText7.text.toString()


        // Verificar si todos los campos (EditTexts y el Spinner) tienen valores
        val allFilled = nombreProf.isNotEmpty() && primerApellido.isNotEmpty() && segundoApellido.isNotEmpty() && nombres.isNotEmpty() && fechaNac.isNotEmpty() && perimetro.isNotEmpty() && municipio && localidad.isNotEmpty()

        // Habilitar o deshabilitar el botón según si todos los campos tienen valores
        btnUpdate.isEnabled = allFilled
    }

    //Metodo para cargar los datos previos del paciente de la tabla principal
    private fun cargarDatosPaciente(folio: String) {
        val paciente = dbHelper.getPacientePorFolio(folio)
        // Ahora, asigna los valores a los campos de texto, etc.
        if (paciente != null) {
            // Supongamos que tienes TextViews o EditTexts para mostrar los datos
            editText1.setText(paciente.value16)
            editText2.setText(paciente.value4)
            editText3.setText(paciente.value5)
            editText4.setText(paciente.value6)
            editText5.setText(paciente.value7)
            editText6.setText(paciente.value12)
            editText7.setText(paciente.value3)
            editText8.setText(paciente.value9)
            editText9.setText(paciente.value10)
            editText10.setText(paciente.value15)
            editText11.setText(paciente.value17)

            val radioButtonHombre = findViewById<RadioButton>(R.id.radioButtonHombre)
            val radioButtonMujer = findViewById<RadioButton>(R.id.radioButtonMujer)

            when (paciente.value8) {
                "Hombre" -> radioButtonHombre.isChecked = true
                "Mujer" -> radioButtonMujer.isChecked = true
            }
            val municipioPaciente = paciente.value2
            (spinnerMunicipio.adapter as? ArrayAdapter<String>)?.let { adapter ->
                val spinnerPosition = adapter.getPosition(municipioPaciente)
                if (spinnerPosition != -1) {
                    spinnerMunicipio.setSelection(spinnerPosition)
                } else {
                    Toast.makeText(this, "Municipio no encontrado: $municipioPaciente", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            Toast.makeText(this, "Paciente no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

}