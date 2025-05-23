package com.example.marinaaprendeamultiplicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAprendeTablas = findViewById<Button>(R.id.btnAprendeTablas)
        val btnPracticaTablas = findViewById<Button>(R.id.btnPracticaTablas)
        val btnPracticaTodas = findViewById<Button>(R.id.btnPracticaTodas)

        // Botones de la barra de herramientas
        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)
        val btnEstado = findViewById<ImageButton>(R.id.btnEstado)

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
            Toast.makeText(this, "Practica la tabla", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this, ElegirTablaPracticarActivity::class.java)
            startActivity(intent2)
            // En el futuro, reemplazarás esto con:
            // val intent = Intent(this, PracticaTablasActivity::class.java)
            // startActivity(intent)
        }

        btnPracticaTodas.setOnClickListener {
            // Aquí iniciarás la Activity "Practica Todas las Tablas"
            Toast.makeText(this, "Practica todas las tablas", Toast.LENGTH_SHORT).show()
            val intent3 = Intent(this, PracticarTodasActivity::class.java)
            startActivity(intent3)

            btnPerfil.setOnClickListener {
                // Aquí irá el código para abrir la actividad de Perfil
                Toast.makeText(this, "Has clicado perfil", Toast.LENGTH_SHORT).show()
                // val intent = Intent(this, PerfilActivity::class.java)
                // startActivity(intent)
            }

            btnEstado.setOnClickListener {
                // Aquí irá el código para abrir la actividad de Estado
                Toast.makeText(this, "Has clicado estado", Toast.LENGTH_SHORT).show()
                 val intent = Intent(this, EstadoActivity::class.java)
                 startActivity(intent)
            }



        }
    }
}