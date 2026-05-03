
package com.example.marinaaprendeamultiplicar

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class AprenderTablaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NUMERO_TABLA = "NUMERO_TABLA"
    }

    val backgroundDrawables = listOf(
        R.drawable.formasrosa,
        R.drawable.azulcorazones,
        R.drawable.azulestrellas,
        R.drawable.dibujitos,
        R.drawable.difuminado,
        R.drawable.doradoconestrellas,
        R.drawable.rayasazules,
        R.drawable.rosaconcorazones,
        R.drawable.verdecorazones,
        R.drawable.verdeguirnalda,
        R.drawable.estrellitas
    )

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprender_tabla)

        val numeroTabla = intent.getIntExtra(EXTRA_NUMERO_TABLA, 1)
        generateTabla(numeroTabla)

        mediaPlayer = when (numeroTabla) {
            1 -> MediaPlayer.create(this, R.raw.tabladel01)
            2 -> MediaPlayer.create(this, R.raw.tabladel02)
            3 -> MediaPlayer.create(this, R.raw.tabladel03)
            4 -> MediaPlayer.create(this, R.raw.tabladel04)
            5 -> MediaPlayer.create(this, R.raw.tabladel05)
            6 -> MediaPlayer.create(this, R.raw.tabladel06)
            7 -> MediaPlayer.create(this, R.raw.tabladel07)
            8 -> MediaPlayer.create(this, R.raw.tabladel08)
            9 -> MediaPlayer.create(this, R.raw.tabladel09)
            10 -> MediaPlayer.create(this, R.raw.tabladel10)
            else -> null
        }

        // Set random background
        val randomIndex = Random.nextInt(backgroundDrawables.size)
        val randomBackground = backgroundDrawables[randomIndex]
        findViewById<ConstraintLayout>(R.id.clAprenderTabla).background = getDrawable(randomBackground)

        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    private fun generateTabla(numeroTabla: Int) {
        val tvAtribucion: TextView = findViewById(R.id.tvAtribucion)
        val tablaLayout: LinearLayout = findViewById(R.id.tablaLayout)
        // Get the TextView from the layout
        val tablaTextView: TextView = findViewById(R.id.tablaTextView)

        val multiplicationLine = "TABLA DEL $numeroTabla\n\n"
        tablaTextView.append(multiplicationLine)

        for (i in 1..10) {
            // Append each multiplication line to the TextView
            val multiplicationLine = "$numeroTabla x $i = ${numeroTabla * i}\n"
            tablaTextView.append(multiplicationLine)
        }
        // 2. Lógica de atribuciones de FiftySounds
        val obra = when (numeroTabla) {
            1 -> "A day to remember"
            2 -> "Candytown"
            3 -> "A world of colour"
            4 -> "Beautiful morning"
            5 -> "Flowers in your hair"
            6 -> "Good vibes"
            7 -> "Happy Day"
            8 -> "Little Nicholas"
            9 -> "Smile for me"
            10 -> "The little gangster"
            else -> ""
        }

        if (obra.isNotEmpty()) {
            val textoAtribucion = "Obra: $obra\nMúsica de https://www.fiftysounds.com/es"
            tvAtribucion.text = textoAtribucion
        }
    }
    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}