

package com.example.marinaaprendeamultiplicar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ElegirTablaPracticarActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegir_tabla_practicar)

        // Configurar listeners para los botones
        val buttons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.button10)
        )

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                val tablaSeleccionada = i + 1 // +1 porque los índices van de 0 a 9
                val intent = Intent(this, PracticarTablaActivity::class.java)
                intent.putExtra("NUMERO_TABLA", tablaSeleccionada)
                startActivity(intent)
            }
        }
    }
}