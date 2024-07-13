package com.example.marinaaprendeamultiplicar

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class AprenderTablaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aprender_tabla)
        val tablaSelec = intent.extras?.getInt("NUMERO_TABLA").toString().orEmpty()

        val tvTabla = findViewById<TextView>(R.id.tvTabla)
        tvTabla.text = tablaSelec


        // Fila 1
        val tvTabla1 = findViewById<TextView>(R.id.tvTabla1)
        tvTabla1.text = tablaSelec
        val tvResult1 = findViewById<TextView>(R.id.tvResult1)
        val result1 = tablaSelec.toInt() * 1
        tvResult1.text = result1.toString()

        // Fila 2
        val tvTabla2 = findViewById<TextView>(R.id.tvTabla2)
        tvTabla2.text = tablaSelec
        val tvResult2 = findViewById<TextView>(R.id.tvResult2)
        val result2 = tablaSelec.toInt() * 2
        tvResult2.text = result2.toString()

        // Fila 3
        val tvTabla3 = findViewById<TextView>(R.id.tvTabla3)
        tvTabla3.text = tablaSelec
        val tvResult3 = findViewById<TextView>(R.id.tvResult3)
        val result3 = tablaSelec.toInt() * 3
        tvResult3.text = result3.toString()

        // Fila 4
        val tvTabla4 = findViewById<TextView>(R.id.tvTabla4)
        tvTabla4.text = tablaSelec
        val tvResult4 = findViewById<TextView>(R.id.tvResult4)
        val result4 = tablaSelec.toInt() * 4
        tvResult4.text = result4.toString()

        // Fila 5
        val tvTabla5 = findViewById<TextView>(R.id.tvTabla5)
        tvTabla5.text = tablaSelec
        val tvResult5 = findViewById<TextView>(R.id.tvResult5)
        val result5 = tablaSelec.toInt() * 5
        tvResult5.text = result5.toString()

        // Fila 6
        val tvTabla6 = findViewById<TextView>(R.id.tvTabla6)
        tvTabla6.text = tablaSelec
        val tvResult6 = findViewById<TextView>(R.id.tvResult6)
        val result6 = tablaSelec.toInt() * 6
        tvResult6.text = result6.toString()

        // Fila 7
        val tvTabla7 = findViewById<TextView>(R.id.tvTabla7)
        tvTabla7.text = tablaSelec
        val tvResult7 = findViewById<TextView>(R.id.tvResult7)
        val result7 = tablaSelec.toInt() * 7
        tvResult7.text = result7.toString()

        // Fila 8
        val tvTabla8 = findViewById<TextView>(R.id.tvTabla8)
        tvTabla8.text = tablaSelec
        val tvResult8 = findViewById<TextView>(R.id.tvResult8)
        val result8 = tablaSelec.toInt() * 8
        tvResult8.text = result8.toString()

        // Fila 9
        val tvTabla9 = findViewById<TextView>(R.id.tvTabla9)
        tvTabla9.text = tablaSelec
        val tvResult9 = findViewById<TextView>(R.id.tvResult9)
        val result9 = tablaSelec.toInt() * 9
        tvResult9.text = result9.toString()

        // Fila 9
        val tvTabla10 = findViewById<TextView>(R.id.tvTabla10)
        tvTabla10.text = tablaSelec
        val tvResult10 = findViewById<TextView>(R.id.tvResult10)
        val result10 = tablaSelec.toInt() * 10
        tvResult10.text = result10.toString()


    }
}