package com.example.marinaaprendeamultiplicar

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Victoria : AppCompatActivity() {
    var tvMsgTiempo: TextView? = null
    var tvTiempo: TextView? = null
    var tvMsgTiempoAnterior: TextView? = null
    var tvTiempoAnterior: TextView? = null

    // 1. Declarar la variable del reproductor
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_victoria)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvMsgTiempo = findViewById(R.id.tvMsgTiempo)
        tvTiempo = findViewById(R.id.tvTiempo)
        tvMsgTiempoAnterior = findViewById(R.id.tvMsgTiempoAnterior)
        tvTiempoAnterior = findViewById(R.id.tvTiempoAnterior)

        // 2. Inicializar y reproducir el sonido de victoria
        mediaPlayer = MediaPlayer.create(this, R.raw.victoria)
        mediaPlayer?.start()

        // Dentro del onCreate de VictoriaActivity
        val tiempoMilis = intent.getLongExtra("TIEMPO_TOTAL", 0)
        val tabla = intent.getIntExtra("TABLA_REALIZADA", 1)

// Convertir milisegundos a formato MM:SS para mostrar a Marina
        val segundos = (tiempoMilis / 1000) % 60
        val minutos = (tiempoMilis / (1000 * 60)) % 60
        val tiempoFormateado = String.format("%02d:%02d", minutos, segundos)

        tvTiempo?.text = tiempoFormateado

// Ahora ya puedes guardar 'tiempoFormateado' o 'tiempoMilis' en tu DB
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}