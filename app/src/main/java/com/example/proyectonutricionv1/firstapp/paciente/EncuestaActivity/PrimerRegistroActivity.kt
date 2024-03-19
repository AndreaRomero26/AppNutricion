package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

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
import android.widget.Spinner
import android.widget.TextView
import com.example.proyectonutricionv1.R
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
    private lateinit var spinnerMunicipio: Spinner
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText
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
        spinnerMunicipio = findViewById(R.id.spinnerMunicipio)
        editText7 = findViewById(R.id.editTextLocalidad)
        editText8 = findViewById(R.id.editTextSexo)

        val textViewFecha = findViewById<TextView>(R.id.respuesta_fecha)
        btn_sig_encuesta = findViewById<Button>(R.id.btn_sig_encuesta)
        val intent_sig_encuesta = Intent(this, EncuestaActivity::class.java)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        textViewFecha.setText(formattedDate)

        ArrayAdapter.createFromResource(
            this,
            R.array.municipios_array,
            R.layout.spinner_item // Usa aquí tu layout personalizado
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item) // Usa el mismo layout para el desplegable, o crea otro si quieres diferenciarlo
            spinnerMunicipio.adapter = adapter
        }

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

        spinnerMunicipio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                checkEditTexts()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Inicialmente, deshabilitar el botón
        btn_sig_encuesta.isEnabled = false

        btn_sig_encuesta.setOnClickListener {
            val value1 = spinnerMunicipio.selectedItem.toString()
            val value2 = editText7.text.toString()
            val value3 = editText2.text.toString()
            val value4 = editText3.text.toString()
            val value5 = editText4.text.toString()
            val value6 = editText5.text.toString()
            val value7 = editText6.text.toString()
            val value8 = editText8.text.toString()
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
    // Manejar clic para abrir el DatePickerDialog
            editText5.setOnClickListener {
                showDatePickerDialog()
            }

    // Manejar el foco para abrir el DatePickerDialog cuando se navega con el botón "Siguiente"
            editText5.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) showDatePickerDialog()
            }
    }

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

    private fun checkEditTexts() {
        val nombreProf = editText1.text.toString()
        val primerApellido = editText2.text.toString()
        val segundoApellido = editText3.text.toString()
        val nombres = editText4.text.toString()
        val fechaNac = editText5.text.toString()
        val perimetro = editText6.text.toString()
        val municipio = spinnerMunicipio.selectedItemPosition > 0 // Asume que la posición 0 es el prompt "Seleccione un Municipio"
        val localidad = editText7.text.toString()
        val sexo = editText8.text.toString()

        // Verificar si todos los campos (EditTexts y el Spinner) tienen valores
        val allFilled = nombreProf.isNotEmpty() && primerApellido.isNotEmpty() && segundoApellido.isNotEmpty() && nombres.isNotEmpty() && fechaNac.isNotEmpty() && perimetro.isNotEmpty() && municipio && localidad.isNotEmpty() && sexo.isNotEmpty()

        // Habilitar o deshabilitar el botón según si todos los campos tienen valores
        btn_sig_encuesta.isEnabled = allFilled
    }

}