package com.example.marinaaprendeamultiplicar

import android.content.ContentValues
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marinaaprendeamultiplicar.SQLite.SQLite

class Victoria : AppCompatActivity() {
    var tvMsgTiempo: TextView? = null
    var tvTiempo: TextView? = null
    var tvMsgTiempoAnterior: TextView? = null
    var tvTiempoAnterior: TextView? = null

    var tvNuevoRecord: TextView? = null


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

        tvNuevoRecord = findViewById(R.id.tvNuevoRecord)


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
        tvMsgTiempo?.text = "Tiempo partida"

        //---------------
        val admin = SQLite(this)
        val bd = admin.writableDatabase

// 2. Buscamos el registro de la tabla que Marina acaba de practicar
// Usamos el 'tabla' que recibimos por el Intent
        val cursor = bd.rawQuery(
            "SELECT ${SQLite.COLUMN_MEJOR_TIEMPO}, ${SQLite.COLUMN_VECES_SUPERADO}, ${SQLite.COLUMN_VECES_BATIDO} " +
                    "FROM ${SQLite.TABLE_ESTADO} WHERE ${SQLite.COLUMN_ID} = ?",
            arrayOf(tabla.toString())
        )

        if (cursor.moveToFirst()) {
            // Obtenemos los valores actuales de la DB
            val mejorTiempoDB = cursor.getDouble(0) // Es tipo REAL (99.99 por defecto)
            val vecesSuperado = cursor.getInt(1)
            val vecesBatido = cursor.getInt(2)

            // Mostramos el tiempo anterior en el TextView
            // Si es 99.99 significa que nunca se ha hecho
            // Mostramos el tiempo anterior con formato uniforme MM:SS
            if (mejorTiempoDB == 99.99) {
                tvTiempoAnterior?.text = "--:--"
            } else {
                // Extraemos los minutos (parte entera) y los segundos (parte decimal)
                val minsAnt = mejorTiempoDB.toInt()
                val segsAnt = Math.round((mejorTiempoDB - minsAnt) * 100).toInt()

                // Aplicamos el mismo formato que usaste para tvTiempo
                tvTiempoAnterior?.text = String.format("%02d:%02d", minsAnt, segsAnt)
            }

            // 3. LÓGICA DE ACTUALIZACIÓN
            val tiempoActualDouble =
                minutos.toDouble() + (segundos.toDouble() / 100) // Convertimos a formato 0.00
            val values = ContentValues()

            // Siempre sumamos una vez superada
            values.put(SQLite.COLUMN_VECES_SUPERADO, vecesSuperado + 1)

            // Si el tiempo actual es menor al que había en la DB, es un RÉCORD
            if (tiempoActualDouble < mejorTiempoDB) {
                values.put(SQLite.COLUMN_MEJOR_TIEMPO, tiempoActualDouble)
                if (mejorTiempoDB != 99.99) {
                    values.put(SQLite.COLUMN_VECES_BATIDO, vecesBatido + 1)

                }


                tvNuevoRecord?.text = "¡NUEVO RÉCORD!"
                tvNuevoRecord?.setTextColor(Color.RED)
            }

            // Guardamos los cambios en la base de datos
            bd.update(
                SQLite.TABLE_ESTADO,
                values,
                "${SQLite.COLUMN_ID} = ?",
                arrayOf(tabla.toString())
            )
        }

        cursor.close()
        bd.close()

        //---------------

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