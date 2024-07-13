package com.example.marinaaprendeamultiplicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ElegirTablaAprenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegir_tabla_aprender)

        val edtNumeroTabla = findViewById<EditText>(R.id.edtNumeroTabla)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        btnContinuar.setOnClickListener {
            val numeroTablaString = edtNumeroTabla.text.toString()
            if (numeroTablaString.isNotEmpty()) {
                val numeroTabla = numeroTablaString.toInt()
                // Inicia la siguiente Activity y pasa el número de la tabla
                val intent = Intent(this, AprenderTablaActivity::class.java)
                intent.putExtra("NUMERO_TABLA", numeroTabla)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, escribe un número", Toast.LENGTH_SHORT).show()
            }
        }
    }
}