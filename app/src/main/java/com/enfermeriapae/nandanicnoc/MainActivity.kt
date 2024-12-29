package com.enfermeriapae.nandanicnoc

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnIdioma: Button

    // Mapa de idiomas y bases de datos asociadas
    private val languageDatabaseMap = mapOf(
        "English" to arrayOf("db_ing.db", "db_2ing.db", "db_3ing.db", "", "db_4ing.db"),
        "Español" to arrayOf("db_esp.db", "db_2esp.db", "db_3esp.db", "db_cie10.db", "db_4esp.db")
        // Puedes agregar más idiomas según sea necesario
    )

    // Idioma seleccionado (por defecto Español)
    private var selectedLanguage = "Español"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializa el botón
        btnIdioma = findViewById(R.id.btn_select_language)

        // Configurar el botón para mostrar el diálogo
        btnIdioma.setOnClickListener {
            showLanguageDialog()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

    // Mostrar el diálogo para seleccionar idioma
    private fun showLanguageDialog() {
        val languages = languageDatabaseMap.keys.toTypedArray()  // Obtener las claves del mapa
        val currentLanguageIndex = languages.indexOf(selectedLanguage)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar idioma")
        builder.setSingleChoiceItems(languages, currentLanguageIndex) { dialog, which ->
            selectedLanguage = languages[which] // Actualizar el idioma seleccionado
        }
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            loadDatabasesForLanguage(selectedLanguage)
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()  // No hacer nada si se cancela
        }

        builder.create().show()
    }

    // Cargar las bases de datos correspondientes según el idioma seleccionado
    private fun loadDatabasesForLanguage(language: String) {
        val databases = languageDatabaseMap[language]

        if (databases != null) {
            // Aquí puedes cargar las bases de datos según el idioma seleccionado
            // Simulación de carga de bases de datos
            Toast.makeText(this, "Cargando bases de datos para $language: ${databases.joinToString(", ")}", Toast.LENGTH_SHORT).show()

            // Lógica para cargar las bases de datos realmente
            // Por ejemplo, abrir las bases de datos correspondientes según `databases`
        } else {
            Toast.makeText(this, "No se encontraron bases de datos para el idioma seleccionado.", Toast.LENGTH_SHORT).show()
        }

    }

}