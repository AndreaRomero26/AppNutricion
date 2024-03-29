package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import com.example.proyectonutricionv1.firstapp.MainMenu
import java.util.*

class GuardarRActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var value19: String
    private lateinit var value20: String
    private lateinit var btnInst30: Button
    private lateinit var btnInst60: Button
    private lateinit var btnInst90: Button
    private lateinit var btnDosis1: Button
    private lateinit var btnDosis2: Button
    private var lastButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardar_ractivity)

        dbHelper = DBHelper(this)

        val btnGenerarDB = findViewById<Button>(R.id.btn_generarDB)
        btnInst30 = findViewById<Button>(R.id.btn_inst30)
        btnInst60 = findViewById<Button>(R.id.btn_inst60)
        btnInst90 = findViewById<Button>(R.id.btn_inst90)
        btnDosis1 = findViewById<Button>(R.id.btn_inst_1vez)
        btnDosis2 = findViewById<Button>(R.id.btn_inst_2vez)

        val textViewClasificacion = findViewById<TextView>(R.id.Respuesta_inseguridad)
        val textViewLocalidad = findViewById<TextView>(R.id.Respuesta_localidad)
        val textViewNombre = findViewById<TextView>(R.id.Respuesta_nombre)
        val textViewBrazo = findViewById<TextView>(R.id.Respuesta_diametro)
        val textViewFolio = findViewById<TextView>(R.id.Respuesta_folio)

        val value2 = intent.getStringExtra("Municipio")!!
        val value3 = intent.getStringExtra("Localidad")!!
        val value4 = intent.getStringExtra("PrimerApellido")!!
        val value5 = intent.getStringExtra("SegundoApellido")!!
        val value6 = intent.getStringExtra("Nombres")!!
        val value7 = intent.getStringExtra("FechaNacimiento")!!
        val value8 = intent.getStringExtra("Sexo")!!
        val value9 = intent.getStringExtra("Estatura")!!
        val value10 = intent.getStringExtra("Peso")!!
        val value12 = intent.getStringExtra("Perimetro")!!
        val value12D = intent.getStringExtra("Perimetro")!!.toDoubleOrNull()
        var value13=""
        if (value12D != null) {
            // El valor se convirtió correctamente a Double
            // Puedes utilizar value12 como un número decimal
           if (value12D<=11.5){
               value13="Desnutricion grave"
           }
           else if (value12D>11.5 && value12D<=12.5){
                value13="Desnutricion moderada"
            }
           else if (value12D>12.5 && value12D<=13.5){
               value13="Riesgo de desnutricion"
           }
           else if (value12D>13.5){
               value13="Sin desnutricion"
           }
        } else {
            // El valor no se pudo convertir a Double
            value13="Error"
        }

        val value14 = intent.getStringExtra("clasificacion")!!
        val value15 = intent.getStringExtra("Tutor")!!
        val value16 = intent.getStringExtra("COC")!!
        val value17 = intent.getStringExtra("Padrino")!!
        val value1 = generarFolio(value2, value4, value6, value8)

        val nombreCompleto = "$value4 $value5 $value6"
        val ubicacionCompleta = "$value2 $value3"

        val intentMainMenu = Intent(this, MainMenu::class.java)


        textViewClasificacion.text = value14
        textViewLocalidad.text = ubicacionCompleta
        textViewNombre.text = nombreCompleto
        textViewBrazo.text = value12
        textViewFolio.text=value1

        btnInst30.setOnClickListener {
            value19 = "30"
            changeButtonInstColor(btnInst30)
        }

        btnInst60.setOnClickListener {
            value19 = "60"
            changeButtonInstColor(btnInst60)
        }

        btnInst90.setOnClickListener {
            value19 = "90"
            changeButtonInstColor(btnInst90)
        }
        btnDosis1.setOnClickListener {
            value20 = "1"
            changeButtonDosisColor(btnDosis1)
        }
        btnDosis2.setOnClickListener {
            value20 = "2"
            changeButtonDosisColor(btnDosis2)
        }
        btnGenerarDB.setOnClickListener {
            dbHelper.insertData(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value12, value13, value14, value15, value16, value17, value19, value20)
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

    }
    fun changeButtonInstColor(button: Button) {
        btnInst30.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOriginal))
        btnInst60.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOriginal))
        btnInst90.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOriginal))

        // Cambiar el color de fondo del botón presionado a rosa
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed))

        // Actualizar el último botón presionado
        lastButton = button
    }
    fun changeButtonDosisColor(button: Button) {
        btnDosis1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOriginal))
        btnDosis2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOriginal))

        // Cambiar el color de fondo del botón presionado a rosa
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed))

        // Actualizar el último botón presionado
        lastButton = button
    }
}
fun generarFolio(municipio: String, primerApellido: String, primerNombre: String, sexo: String): String {
    val codigoMunicipio = obtenerCodigoMunicipio(municipio)
    val letrasApellido = primerApellido.take(2).uppercase(Locale.getDefault())
    val letraNombre = primerNombre.first().uppercaseChar().toString()
    val letraSexo = if(sexo.startsWith("M", ignoreCase = true)) "F" else "M"
    val letraAleatoria = ('A'..'Z').random().toString()
    val numeroAleatorio = (1..9).random().toString()

    return "$codigoMunicipio$letrasApellido$letraNombre$letraSexo$letraAleatoria$numeroAleatorio"
}

fun obtenerCodigoMunicipio(municipio: String): String {
    return when (municipio.uppercase(Locale.getDefault())) {
        "AHUMADA" -> "001"
        "ALDAMA" -> "002"
        "ALLENDE" -> "003"
        "AQUILES SERDAN" -> "004"
        "ASCENSION" -> "005"
        "BACHINIVA" -> "006"
        "BALLEZA" -> "007"
        "BATOPILAS" -> "008"
        "BOCOYNA" -> "009"
        "BUENAVENTURA" -> "010"
        "CAMARGO" -> "011"
        "CARICHI" -> "012"
        "CASAS GRANDES" -> "013"
        "CORONADO" -> "014"
        "COYAME" -> "015"
        "LA CRUZ" -> "016"
        "CUAUHTEMOC" -> "017"
        "CUSIHUIRIACHI" -> "018"
        "CHIHUAHUA" -> "019"
        "CHINIPAS" -> "020"
        "DELICIAS" -> "021"
        "BELISARIO DOMINGUEZ" -> "022"
        "GALEANA" -> "023"
        "SANTA ISABEL" -> "024"
        "GOMEZ FARIAS" -> "025"
        "GRAN MORELOS" -> "026"
        "GUACHOCHI" -> "027"
        "GUADALUPE" -> "028"
        "GUADALUPE Y CALVO" -> "029"
        "GUAZAPARES" -> "030"
        "GUERRERO" -> "031"
        "HIDALGO DEL PARRAL" -> "032"
        "HUEJOTITAN" -> "033"
        "IGNACIO ZARAGOZA" -> "034"
        "JANOS" -> "035"
        "JIMENEZ" -> "036"
        "JUAREZ" -> "037"
        "JULIMES" -> "038"
        "LOPEZ" -> "039"
        "MADERA" -> "040"
        "MAGUARICHI" -> "041"
        "MANUEL BENAVIDES" -> "042"
        "MATACHI" -> "043"
        "MATAMOROS" -> "044"
        "MEOQUI" -> "045"
        "MORELOS" -> "046"
        "MORIS" -> "047"
        "NAMIQUIPA" -> "048"
        "NONOAVA" -> "049"
        "NUEVO CASAS GRANDES" -> "050"
        "OCAMPO" -> "051"
        "OJINAGA" -> "052"
        "PRAXEDIS G. GUERRERO" -> "053"
        "RIVA PALACIO" -> "054"
        "ROSALES" -> "055"
        "ROSARIO" -> "056"
        "SAN FRANCISCO DE BORJA" -> "057"
        "SAN FRANCISCO DE CONCHOS" -> "058"
        "SAN FRANCISCO DEL ORO" -> "059"
        "SANTA BARBARA" -> "060"
        "SATEVO" -> "061"
        "SAUCILLO" -> "062"
        "TEMOSACHIC" -> "063"
        "EL TULE" -> "064"
        "URIQUE" -> "065"
        "URUACHI" -> "066"
        "VALLE DE ZARAGOZA" -> "067"
        else -> "000" // Un valor por defecto o manejo de error.
    }
}