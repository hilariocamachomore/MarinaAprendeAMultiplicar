package com.example.marinaaprendeamultiplicar

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.math.log
import kotlin.random.Random
import android.util.Log
import android.graphics.Color

class PracticarTodasActivity : AppCompatActivity() {

    companion object {
        const val NUMERO_TABLA = "NUMERO_TABLA"
    }

    private var startTime: Long = 0
    private var tiempoFinal: Long = 0
    private lateinit var tvPregunta: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var respuesta1: Button
    private lateinit var respuesta2: Button
    private lateinit var respuesta3: Button
    private lateinit var respuesta4: Button
    private lateinit var respuesta5: Button
    private lateinit var respuesta6: Button

    private var numeroTabla: Int = 1
    private var respuestaCorrecta = 1
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler(Looper.getMainLooper())
    private var previousQuestion: Pair<Int, Int>? = null
    private var correctSound: MediaPlayer? = null
    private var incorrectSound: MediaPlayer? = null
    private var endTimeSound: MediaPlayer? = null
    private var victorySound: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practicar_todas)
        //Log.d("HECTOR OnCreate", " OnCreate")


        // Initialize UI elements
        tvPregunta = findViewById(R.id.tvPregunta)
        progressBar = findViewById(R.id.progressBar)
        respuesta1 = findViewById(R.id.respuesta1)
        respuesta2 = findViewById(R.id.respuesta2)
        respuesta3 = findViewById(R.id.respuesta3)
        respuesta4 = findViewById(R.id.respuesta4)
        respuesta5 = findViewById(R.id.respuesta5)
        respuesta6 = findViewById(R.id.respuesta6)

        //sonidos
        correctSound = MediaPlayer.create(this, R.raw.correcto)
        incorrectSound = MediaPlayer.create(this, R.raw.incorrecto)
        endTimeSound = MediaPlayer.create(this, R.raw.fin_tiempo)
        //victorySound = MediaPlayer.create(this, R.raw.victoria)


        // Initialize progress bar
        progressBar.max = 30
        progressBar.progress = 0

        generarPregunta()
        startTime = System.currentTimeMillis() // Captura el momento en que empieza a jugar

        // Set click listeners for answer buttons
        respuesta1.setOnClickListener { comprobarRespuesta(respuesta1.text.toString().toInt()) }
        respuesta2.setOnClickListener { comprobarRespuesta(respuesta2.text.toString().toInt()) }
        respuesta3.setOnClickListener { comprobarRespuesta(respuesta3.text.toString().toInt()) }
        respuesta4.setOnClickListener { comprobarRespuesta(respuesta4.text.toString().toInt()) }
        respuesta5.setOnClickListener { comprobarRespuesta(respuesta5.text.toString().toInt()) }
        respuesta6.setOnClickListener { comprobarRespuesta(respuesta6.text.toString().toInt()) }
    }

    private fun generarPregunta() {
        //Log.d("HECTOR ProgressBarProgress", "Progress: ${progressBar.progress}")
        //Log.d("HECTOR numerotabla", "numerotabla: $numeroTabla")
        // Generate a random number between 1 and 10
        numeroTabla = Random.nextInt(1, 11)
        var numAleatorio: Int

        // Generate a question different from the previous one
        do {
            numAleatorio = Random.nextInt(1, 11)
        } while (previousQuestion != null && previousQuestion == Pair(numeroTabla, numAleatorio))

        // Store the current question as the previous question for the next iteration
        previousQuestion = Pair(numeroTabla, numAleatorio)

        // Display the question
        tvPregunta.text = "$numeroTabla x $numAleatorio ="

        // Calculate and store the correct answer
        respuestaCorrecta = numeroTabla * numAleatorio

        // Generate answer options (including the correct one)
        val respuestas = mutableListOf<Int>()
        respuestas.add(respuestaCorrecta)


        // Generate incorrect answers
        while (respuestas.size < 6) {
            val respuestaIncorrecta = Random.nextInt(1, 101) // Adjust range if needed
            if (respuestaIncorrecta != respuestaCorrecta && !respuestas.contains(respuestaIncorrecta)) {
                respuestas.add(respuestaIncorrecta)
            }
        }

        // Shuffle the answers
        respuestas.shuffle()

        //Log.d("HECTOR respuestas", "respuestas: $respuestas")

        // Assign answers to buttons
        respuesta1.text = respuestas[0].toString()
        respuesta2.text = respuestas[1].toString()
        respuesta3.text = respuestas[2].toString()
        respuesta4.text = respuestas[3].toString()
        respuesta5.text = respuestas[4].toString()
        respuesta6.text = respuestas[5].toString()

        iniciarCuentaAtras()
    }

    private fun iniciarCuentaAtras() {
        countDownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // You can update a timer UI element here if needed
            }

            override fun onFinish() {
                // Time's up! Decrement progress and play sound
                endTimeSound?.start() // Play the correct sound
                if (progressBar.progress > 0) {
                    progressBar.progress--
                }
                // Play time's up sound
                // ... (Add code to play time's up sound) ...
                tvPregunta.setTextColor(Color.RED)
                mostrarRespuestaCorrectaYContinuar()
            }
        }.start()
    }

    private fun comprobarRespuesta(respuestaSeleccionada: Int) {
        countDownTimer.cancel() // Cancel the timer

        if (respuestaSeleccionada == respuestaCorrecta) {
            correctSound?.start() // Play the correct sound
            progressBar.progress++
            // Play correct answer sound
            // ... (Add code to play correct answer sound) ...

            if (progressBar.progress == progressBar.max) {
                enviarAVictoria()
            } else {
                //generarPregunta() // Generate next question
                tvPregunta.setTextColor(Color.GREEN)
                mostrarRespuestaCorrectaYContinuar()
            }
        } else {
            // Incorrect answer: decrement progress and play sound
            incorrectSound?.start() // Play the incorrect sound
            if (progressBar.progress >= 3) {
                progressBar.progress -= 3
            } else {
                progressBar.progress = 0
            }
            // Play incorrect answer sound
            // ... (Add code to play incorrect answer sound) ...
            tvPregunta.setTextColor(Color.RED)
            mostrarRespuestaCorrectaYContinuar()
        }
    }

    private fun mostrarRespuestaCorrectaYContinuar() {
        countDownTimer.cancel()
        // Show correct answer in tvPregunta
        tvPregunta.text = "${tvPregunta.text} $respuestaCorrecta"

        // Disable buttons
        desactivarBotones()

        // Wait for 2 seconds and then generate the next question
        handler.postDelayed({
            tvPregunta.setTextColor(Color.WHITE)
            habilitarBotones()
            generarPregunta()
        }, 1000)
    }

//    private fun mostrarVictoriaYVolverAlMenu() {
//        countDownTimer.cancel()
//        // Play victory sound
//        // ... (Add code to play victory sound) ...
//
//        // Show victory message in tvPregunta
//        victorySound?.start()
//        tvPregunta.text = "¡¡VICTORIA!!"
//
//        // Disable buttons
//        desactivarBotones()
//
//        // Wait for 3 seconds and then navigate to main menu
//        handler.postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish() // Optional: Finish the current activity
//        }, 4000)
//    }

    private fun enviarAVictoria() {
        countDownTimer.cancel()
        // 1. Calcular el tiempo que ha tardado (en milisegundos)
        tiempoFinal = System.currentTimeMillis() - startTime

        // 2. Sonido y mensaje visual
        //victorySound?.start()

        //tvPregunta.text = "¡¡CONSEGUIDO!!"
        //tvPregunta.setTextColor(Color.YELLOW)

        desactivarBotones()

        // 3. Esperar un momento y saltar a VictoriaActivity
        handler.postDelayed({
            val intent = Intent(this, Victoria::class.java) // Asegúrate de crear esta Activity

            // Pasamos los datos necesarios para tu futura base de datos
            intent.putExtra("TIEMPO_TOTAL", tiempoFinal) // En milisegundos
            intent.putExtra("TABLA_REALIZADA", 11)

            startActivity(intent)
            finish()
        }, 500) // Reducido a 1/2 segundo para que no sea tan larga la espera
    }

    private fun desactivarBotones() {
        respuesta1.isEnabled = false
        respuesta2.isEnabled = false
        respuesta3.isEnabled = false
        respuesta4.isEnabled = false
        respuesta5.isEnabled = false
        respuesta6.isEnabled = false
    }

    private fun habilitarBotones() {
        respuesta1.isEnabled = true
        respuesta2.isEnabled = true
        respuesta3.isEnabled = true
        respuesta4.isEnabled = true
        respuesta5.isEnabled = true
        respuesta6.isEnabled = true
    }

    override fun onBackPressed() {
        if (::countDownTimer.isInitialized) { // Verificar si countDownTimer está inicializado
            countDownTimer.cancel()
        }

        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        correctSound?.release()
        correctSound = null
        incorrectSound?.release()
        incorrectSound = null
        endTimeSound?.release()
        endTimeSound = null
        victorySound?.release()
        victorySound = null
    }
}