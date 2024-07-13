package com.example.marinaaprendeamultiplicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAprendeTablas = findViewById<Button>(R.id.btnAprendeTablas)
        val btnPracticaTablas = findViewById<Button>(R.id.btnPracticaTablas)
        val btnPracticaTodas = findViewById<Button>(R.id.btnPracticaTodas)

        btnAprendeTablas.setOnClickListener {
            // Aquí iniciarás la Activity "Aprende las Tablas"
            // Por ahora, mostraremos un Toast como ejemplo
            Toast.makeText(this, "Aprende las Tablas", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ElegirTablaAprenderActivity::class.java)
            startActivity(intent)
            // En el futuro, reemplazarás esto con:
            // val intent = Intent(this, AprendeTablasActivity::class.java)
            // startActivity(intent)
        }

        btnPracticaTablas.setOnClickListener {
            // Aquí iniciarás la Activity "Practica las Tablas"
            Toast.makeText(this, "Practica las Tablasssssss", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this, ElegirTablaPracticarActivity::class.java)
            startActivity(intent2)
            // En el futuro, reemplazarás esto con:
            // val intent = Intent(this, PracticaTablasActivity::class.java)
            // startActivity(intent)
        }

        btnPracticaTodas.setOnClickListener {
            // Aquí iniciarás la Activity "Practica Todas las Tablas"
            Toast.makeText(this, "Practica Todas las Tablas", Toast.LENGTH_SHORT).show()
            // En el futuro, reemplazarás esto con:
            // val intent = Intent(this, PracticaTodasActivity::class.java)
            // startActivity(intent)
        }
    }
}