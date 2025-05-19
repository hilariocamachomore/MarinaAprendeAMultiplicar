package com.example.marinaaprendeamultiplicar

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
//import androidx.compose.ui.semantics.text
import androidx.core.content.ContextCompat
import com.example.marinaaprendeamultiplicar.R // Asegúrate de que el R sea el correcto

class EstadoActivity : AppCompatActivity() {

    private lateinit var tablaEstado: TableLayout

    // Datos de ejemplo (deberás obtener estos datos de tu lógica de la aplicación)
    data class EstadoTabla(val nombreTabla: String, val realizado: Boolean, val mejorTiempo: String, val premios: Int)

    private val datosTablas = listOf(
        EstadoTabla("Tabla del 1", true, "00:45", 2),
        EstadoTabla("Tabla del 2", true, "00:52", 1),
        EstadoTabla("Tabla del 3", false, "-", 0),
        EstadoTabla("Tabla del 4", true, "01:10", 3),
        EstadoTabla("Tabla del 5", false, "-", 0),
        EstadoTabla("Tabla del 6", true, "00:58", 1),
        EstadoTabla("Tabla del 7", false, "-", 0),
        EstadoTabla("Tabla del 8", true, "01:15", 2),
        EstadoTabla("Tabla del 9", false, "-", 0),
        EstadoTabla("Tabla del 10", true, "00:40", 3),
        EstadoTabla("Todas", false, "-", 0) // Fila para "Todas las tablas"
        // Puedes añadir una duodécima fila si es necesario, o ajustar el bucle
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado)

        tablaEstado = findViewById(R.id.tablaEstado)

        generarFilasTabla()
    }

    private fun generarFilasTabla() {
        for (i in datosTablas.indices) { // Itera hasta 11 para las tablas del 1 al 10 y "Todas"
            val estado = datosTablas[i]

            val tableRow = TableRow(this)
            val layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            tableRow.layoutParams = layoutParams

            // Alternar colores de fondo para mejor legibilidad
            if (i % 2 == 0) {
                tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_gray))
            } else {
                tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            }

            // Columna "Tabla"
            tableRow.addView(crearTextViewCelda(estado.nombreTabla))

            // Columna "Realizado"
            val realizadoTexto = if (estado.realizado) "Sí" else "No"
            tableRow.addView(crearTextViewCelda(realizadoTexto))

            // Columna "Mejor Tiempo"
            tableRow.addView(crearTextViewCelda(estado.mejorTiempo))

            // Columna "Premios"
            tableRow.addView(crearTextViewCelda(estado.premios.toString()))

            tablaEstado.addView(tableRow)
        }
    }

    private fun crearTextViewCelda(texto: String, esNegrita: Boolean = false): TextView {
        val textView = TextView(this)
        val params = TableRow.LayoutParams(
            0, // width = 0dp para que funcione el weight
            TableRow.LayoutParams.WRAP_CONTENT,
            1f // weight = 1 para que todas las celdas compartan el espacio equitativamente
        )
        textView.layoutParams = params
        textView.text = texto
        textView.gravity = Gravity.CENTER
        textView.setPadding(8, 8, 8, 8) // Añade un poco de padding a las celdas

        if (esNegrita) {
            textView.setTypeface(null, Typeface.BOLD)
        }

        return textView
    }
}