package com.example.marinaaprendeamultiplicar

import android.content.ContentValues
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.example.marinaaprendeamultiplicar.SQLite.SQLite

class EstadoActivity : AppCompatActivity() {

    private lateinit var tablaEstado: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado)

        // Fondo verde para la pantalla
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_9))

        val btnReiniciar = findViewById<Button>(R.id.btnReiniciarResultados)
        tablaEstado = findViewById(R.id.tablaEstado)

        // Añadimos primero una fila de cabecera para que se sepa qué es cada columna
        añadirCabecera()

        // Cargamos los datos
        generarFilasDesdeDB()

        btnReiniciar.setOnClickListener {
            // Creamos el cuadro de diálogo de confirmación
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("¿Estás segur@?")
            builder.setMessage("Se borrarán todos tus logros y volverán a estar a cero.")

            // Si pulsa "SÍ", ejecutamos el reinicio
            builder.setPositiveButton("SÍ,REINICIAR") { _, _ ->
                reiniciarBaseDeDatos()
            }

            // Si pulsa "NO", no hace nada y cierra el diálogo
            builder.setNegativeButton("NO,VOLVER") { dialog, _ ->
                dialog.dismiss()
            }

            // Mostramos el aviso
            val dialog = builder.create()
            dialog.show()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Creamos el Intent para ir a MainActivity
                val intent = Intent(this@EstadoActivity, MainActivity::class.java)

                // Estas banderas limpian la pila de actividades para que no se
                // acumulen pantallas una encima de otra
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                startActivity(intent)
                finish() // Cerramos EstadoActivity
            }
        })
    }

    private fun reiniciarBaseDeDatos() {
        val admin = SQLite(this)
        val bd = admin.writableDatabase

        // Preparamos los valores iniciales
        val values = ContentValues()
        values.put(SQLite.COLUMN_MEJOR_TIEMPO, 99.99)
        values.put(SQLite.COLUMN_VECES_SUPERADO, 0)
        values.put(SQLite.COLUMN_VECES_BATIDO, 0)

        // Actualizamos todas las filas (al no poner cláusula WHERE, afecta a todas)
        bd.update(SQLite.TABLE_ESTADO, values, null, null)
        bd.close()

        // Para que Marina vea los cambios al instante, reiniciamos la Activity
        val intent = intent
        finish() // Cerramos la actual
        startActivity(intent) // La volvemos a abrir
        // Opcional: Quitar la animación para que parezca un refresco instantáneo
        overridePendingTransition(0, 0)
    }
    private fun añadirCabecera() {
        val filaCabecera = TableRow(this)
        filaCabecera.setBackgroundColor(ContextCompat.getColor(this, R.color.black)) // Cabecera en negro para distinguir

        filaCabecera.addView(crearTextViewCelda("TABLA"))
        filaCabecera.addView(crearTextViewCelda("MEJOR T."))
        filaCabecera.addView(crearTextViewCelda("BATIDO"))
        filaCabecera.addView(crearTextViewCelda("SUPERADO"))

        tablaEstado.addView(filaCabecera)
    }

    private fun generarFilasDesdeDB() {
        val admin = SQLite(this)
        val bd = admin.readableDatabase

        val fila = bd.rawQuery("SELECT * FROM ${SQLite.TABLE_ESTADO} ORDER BY ${SQLite.COLUMN_ID} ASC", null)

        var contadorColor = 0

        // Obtenemos los índices de las columnas por su nombre para no fallar
        val indexId = fila.getColumnIndex(SQLite.COLUMN_ID)
        val indexMejorTiempo = fila.getColumnIndex(SQLite.COLUMN_MEJOR_TIEMPO)
        val indexSuperado = fila.getColumnIndex(SQLite.COLUMN_VECES_SUPERADO)
        val indexBatido = fila.getColumnIndex(SQLite.COLUMN_VECES_BATIDO)

        if (fila.moveToFirst()) {
            do {
                // Extraemos los datos usando los índices correctos
                val id = fila.getInt(indexId)
                val mejorTiempo = fila.getDouble(indexMejorTiempo)
                val vecesSuperado = fila.getInt(indexSuperado)
                val vecesBatido = fila.getInt(indexBatido)

                val tableRow = TableRow(this)
                val layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 0, 0, 2)
                tableRow.layoutParams = layoutParams

                // Alternar colores
                if (contadorColor % 2 == 0) {
                    tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_4))
                } else {
                    tableRow.setBackgroundColor(ContextCompat.getColor(this, R.color.light_orange))
                }

                // --- DIBUJAR COLUMNAS ---

                // 1. Nombre de la Tabla
                val nombre = if (id <= 10) "Tabla del $id" else "TODAS"
                tableRow.addView(crearTextViewCelda(nombre))

                // 2. MEJOR TIEMPO
                val tiempoTexto = if (mejorTiempo >= 99.99) {
                    "--:--"
                } else {
                    // Formateamos el Double (ej: 1.25) a String (1:25)
                    String.format("%.2f", mejorTiempo).replace(".", ":").replace(",", ":")
                }
                tableRow.addView(crearTextViewCelda(tiempoTexto))

                // 3. BATIDO
                tableRow.addView(crearTextViewCelda(vecesBatido.toString()))

                // 4. SUPERADO
                tableRow.addView(crearTextViewCelda(vecesSuperado.toString()))

                tablaEstado.addView(tableRow)
                contadorColor++

            } while (fila.moveToNext())
        }
        fila.close()
        //bd.close()
    }

    private fun crearTextViewCelda(texto: String): TextView {
        val textView = TextView(this)
        val params = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

        textView.layoutParams = params
        textView.text = texto
        textView.setTextColor(ContextCompat.getColor(this, R.color.white)) // Blanco
        textView.textSize = 14f // Un poco más pequeño para que quepan bien 4 columnas
        textView.setTypeface(null, Typeface.BOLD) // Negrita
        textView.gravity = Gravity.CENTER
        textView.setPadding(8, 30, 8, 30)

        return textView
    }
}
