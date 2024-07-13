package com.example.marinaaprendeamultiplicar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PracticarTablaActivity : AppCompatActivity() {

    private lateinit var tvPregunta: TextView
    private lateinit var etRespuesta: EditText
    private lateinit var btnComprobar: Button
    private lateinit var tvResultado: TextView

    private var tablaSelec = 0
    private var numeroPregunta = 0
    private var resultadoCorrecto = 0
    private val operacionesFallidas = mutableListOf<Int>()
    private var aciertosConsecutivos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practicar_tabla)

        tvPregunta = findViewById(R.id.tvPregunta)
        etRespuesta = findViewById(R.id.etRespuesta)
        btnComprobar = findViewById(R.id.btnComprobar)
        tvResultado = findViewById(R.id.tvResultado)

        tablaSelec = intent.extras?.getInt("NUMERO_TABLA") ?: 0
        generarPregunta()

        btnComprobar.setOnClickListener {
            comprobarRespuesta()
        }
    }

    private fun generarPregunta() {
        if (operacionesFallidas.isNotEmpty()) {
            numeroPregunta = operacionesFallidas.random()
            operacionesFallidas.remove(numeroPregunta)
        } else {
            numeroPregunta = (1..10).random()
        }
        resultadoCorrecto = tablaSelec * numeroPregunta
        tvPregunta.text = "¿Cuánto es $tablaSelec x $numeroPregunta?"
        etRespuesta.text.clear()
        tvResultado.text = ""
    }

    private fun comprobarRespuesta() {
        val respuestaUsuario = etRespuesta.text.toString().toIntOrNull()
        if (respuestaUsuario == resultadoCorrecto) {
            tvResultado.text = "¡Correcto! ¡Muy bien!"
            aciertosConsecutivos++
            if (aciertosConsecutivos >= 10) {
                // El usuario ha acertado 10 veces seguidas
                tvResultado.text = "¡Felicidades! ¡Te sabes la tabla del $tablaSelec! Sigue practicando."
                // Retrasamos el inicio de la actividad principal para que el usuario pueda leer el mensaje
                tvResultado.postDelayed({
                    val intent = Intent(this, MainActivity::class.java) // Reemplaza MainActivity con el nombre de tu actividad principal
                    startActivity(intent)
                    finish() // Finaliza la actividad actual
                }, 2000) // 2 segundos de retraso
            } else {
                // Reiniciamos la actividad para la siguiente pregunta
                reiniciarActividad()
            }
        } else {
            tvResultado.text = "Incorrecto. Inténtalo de nuevo."
            operacionesFallidas.add(numeroPregunta)
            aciertosConsecutivos = 0 // Reseteamos los aciertos consecutivos
            // Reiniciamos la actividad para la siguiente pregunta
            reiniciarActividad()
        }
    }

    private fun reiniciarActividad() {
        // Limpiamos el EditText y el TextView de resultado
        etRespuesta.text.clear()
        tvResultado.text = ""
        // Reiniciamos la actividad
        val intent = Intent(this, PracticarTablaActivity::class.java)
        intent.putExtra("NUMERO_TABLA", tablaSelec)
        startActivity(intent)
        finish()
    }
}