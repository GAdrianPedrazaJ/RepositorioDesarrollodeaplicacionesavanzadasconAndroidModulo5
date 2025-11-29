package com.example.mapadelugares

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Pedir permiso de notificaciones en Android 13+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (checkSelfPermission(permission) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(permission), 101)
            }
        }
        // Crear canal de notificación (paso 1)
        createNotificationChannel()

        // Obtener botones
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        // Botón para probar la notificación (EL NUEVO)
        val btnNotif = findViewById<Button>(R.id.btnNotificacion)
        btnNotif.setOnClickListener {
            val helper = NotificationHelper(this)
            helper.showNotification("1234")   // ID del usuario que calificó tu foto
        }

        // Centro Comercial
        btn1.setOnClickListener {
            abrirMapa(4.6367, -74.0841, "Centro Comercial")
        }

        // Parque Central
        btn2.setOnClickListener {
            abrirMapa(4.6379, -74.0825, "Parque Central")
        }

        // Plaza Principal
        btn3.setOnClickListener {
            abrirMapa(4.6391, -74.0813, "Plaza Principal")
        }

        // Estadio
        btn4.setOnClickListener {
            abrirMapa(4.6402, -74.0801, "Estadio Municipal")
        }
    }

    private fun abrirMapa(lat: Double, lng: Double, titulo: String) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("lng", lng)
        intent.putExtra("titulo", titulo)
        startActivity(intent)
    }

    // 🔥 Paso 1: Crear el canal de notificaciones
    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                "wear_channel",
                "Wear Notifications",
                android.app.NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(android.app.NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
            } else {
                // Permiso denegado
            }
        }
    }
}