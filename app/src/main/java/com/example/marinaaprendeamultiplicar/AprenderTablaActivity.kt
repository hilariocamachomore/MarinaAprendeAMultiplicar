
package com.example.marinaaprendeamultiplicar

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AprenderTablaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NUMERO_TABLA = "NUMERO_TABLA"
    }

    private lateinit var binding: ActivityAprenderTablaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAprenderTablaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numeroTabla = intent.getIntExtra(EXTRA_NUMERO_TABLA, 1)
        generateTabla(numeroTabla)
    }

    private fun generateTabla(numeroTabla: Int) {
        val tablaLayout: LinearLayout = binding.tablaLayout
        for (i in 1..10) {
            val textView = TextView(this)
            textView.text = "$numeroTabla x $i = ${numeroTabla * i}"
            tablaLayout.addView(textView)
        }
    }
}