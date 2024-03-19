package com.example.proyectonutricionv1.firstapp.paciente.EncuestaActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.proyectonutricionv1.R
import com.example.proyectonutricionv1.firstapp.DBHelper
import java.util.*

class GuardarRActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var value14: String
    private lateinit var value15: String
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
        val value10 = intent.getStringExtra("Perimetro")!!
        val value11="Desnutricion grave"
        val value12 = intent.getStringExtra("clasificacion")!!
        val value13 = intent.getStringExtra("COC")!!
        val value1 = generarFolio(value2, value4, value6, value8)

        val nombreCompleto = "$value4 $value5 $value6"
        val ubicacionCompleta = "$value2 $value3"

        textViewClasificacion.text = value12
        textViewLocalidad.text = ubicacionCompleta
        textViewNombre.text = nombreCompleto
        textViewBrazo.text = value10
        textViewFolio.text=value1

        btnInst30.setOnClickListener {
            value14 = "30"
            changeButtonInstColor(btnInst30)
        }

        btnInst60.setOnClickListener {
            value14 = "60"
            changeButtonInstColor(btnInst60)
        }

        btnInst90.setOnClickListener {
            value14 = "90"
            changeButtonInstColor(btnInst90)
        }
        btnDosis1.setOnClickListener {
            value15 = "1"
            changeButtonDosisColor(btnDosis1)
        }
        btnDosis2.setOnClickListener {
            value15 = "2"
            changeButtonDosisColor(btnDosis2)
        }
        btnGenerarDB.setOnClickListener {
            dbHelper.insertData(value1, value2, value3, value4, value5, value6, value7, value8, value10, value11, value12, value13)
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
    val letraSexo = if(sexo.startsWith("F", ignoreCase = true)) "F" else "M"
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