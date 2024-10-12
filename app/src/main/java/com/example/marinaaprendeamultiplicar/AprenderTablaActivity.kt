package com.example.marinaaprendeamultiplicar

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.text.style.UnderlineSpan

class AprenderTablaActivity : AppCompatActivity() {

    companion object {
        const val NUMERO_TABLA = "NUMERO_TABLA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprender_tabla)

        val numeroTabla = intent.getIntExtra(NUMERO_TABLA, 1)

        val tvTablaTitulo = findViewById<TextView>(R.id.tvTablaTitulo)
        val tvNumeroTabla = findViewById<TextView>(R.id.tvNumeroTabla)

        val tituloSpannable = SpannableString(tvTablaTitulo.text)
        tituloSpannable.setSpan(UnderlineSpan(), 0, tituloSpannable.length, 0)
        tvTablaTitulo.text = tituloSpannable

        val numeroSpannable = SpannableString(tvNumeroTabla.text)
        numeroSpannable.setSpan(UnderlineSpan(), 0, numeroSpannable.length, 0)
        tvNumeroTabla.text = numeroSpannable

        val tvTablaContenido = findViewById<TextView>(R.id.tvTablaContenido)
        tvTablaContenido.text = generarTablaMultiplicarConColores(numeroTabla)
    }

    private fun generarTablaMultiplicarConColores(numero: Int): SpannableString {
        val sb = StringBuilder()
        for (i in 1..10) {
            sb.append("$numero x $i = ${numero * i}\n")
        }

        val spannableString = SpannableString(sb.toString())
        val lines = sb.toString().split("\n")

        var start = 0
        for (i in lines.indices) {
            val end = start + lines[i].length
            val color = when (i + 1) {
                1 -> ContextCompat.getColor(this, R.color.blue_1)
                2 -> ContextCompat.getColor(this, R.color.blue_2)
                3 -> ContextCompat.getColor(this, R.color.blue_3)
                4 -> ContextCompat.getColor(this, R.color.blue_4)
                5 -> ContextCompat.getColor(this, R.color.blue_5)
                6 -> ContextCompat.getColor(this, R.color.blue_6)
                7 -> ContextCompat.getColor(this, R.color.blue_7)
                8 -> ContextCompat.getColor(this, R.color.blue_8)
                9 -> ContextCompat.getColor(this, R.color.blue_9)
                10 -> ContextCompat.getColor(this, R.color.blue_10)
                else -> Color.BLACK // Color por defecto si hay más de 10 líneas
            }
            spannableString.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            start = end + 1 // +1 para incluir el salto de línea
        }

        return spannableString
    }
}