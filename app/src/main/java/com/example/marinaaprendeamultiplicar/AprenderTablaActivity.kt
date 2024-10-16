
package com.example.marinaaprendeamultiplicar

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.marinaaprendeamultiplicar.databinding.ActivityAprenderTablaBinding

class AprenderTablaActivity<ActivityAprenderTablaBinding> : AppCompatActivity() {

    private lateinit var binding: ActivityAprenderTablaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAprenderTablaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedNumber = intent.getIntExtra("selectedNumber", 1) // Get the selected number
        generateTabla(selectedNumber)
    }

    private fun generateTabla(number: Int) {
        val tablaLayout: LinearLayout = binding.tablaLayout
        for (i in 1..10) {
            val textView = TextView(this)
            textView.text = "$number x $i = ${number * i}"
            tablaLayout.addView(textView)
        }
    }
}