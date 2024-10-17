
package com.example.marinaaprendeamultiplicar

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AprenderTablaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NUMERO_TABLA = "NUMERO_TABLA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprender_tabla)

        val numeroTabla = intent.getIntExtra(EXTRA_NUMERO_TABLA, 1)
        generateTabla(numeroTabla)
    }

    private fun generateTabla(numeroTabla: Int) {
        val tablaLayout: LinearLayout = findViewById(R.id.tablaLayout)
        // Get the TextView from the layout
        val tablaTextView: TextView = findViewById(R.id.tablaTextView)

        for (i in 1..10) {
            // Append each multiplication line to the TextView
            val multiplicationLine = "$numeroTabla x $i = ${numeroTabla * i}\n"
            tablaTextView.append(multiplicationLine)
        }
    }
}