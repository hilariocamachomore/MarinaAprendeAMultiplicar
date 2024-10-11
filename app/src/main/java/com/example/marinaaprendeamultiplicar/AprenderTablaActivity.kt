package com.example.marinaaprendeamultiplicar


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AprenderTablaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprender_tabla)

        val tablaLayout = findViewById<LinearLayout>(R.id.tablaLayout)
        val numeroTabla = intent.getIntExtra("NUMERO_TABLA", 1)
        val colors = listOf(
            R.color.blue_1, R.color.blue_2, R.color.blue_3, R.color.blue_4,
            R.color.blue_5, R.color.blue_6, R.color.blue_7, R.color.blue_8,
            R.color.blue_9, R.color.blue_10
        )

        for (i in 1..10) {
            val textView = TextView(this)
            textView.text = "$numeroTabla x $i = ${numeroTabla * i}"
            textView.textSize = 20f
            textView.setTextColor(ContextCompat.getColor(this, colors[i - 1]))
            tablaLayout.addView(textView)
        }
    }
}