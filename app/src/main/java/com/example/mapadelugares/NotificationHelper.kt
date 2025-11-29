package com.example.mapadelugares

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(private val context: Context) {

    private val CHANNEL_ID = "wear_channel"

    fun showNotification(userId: String) {

        // Crear canal ANTES de mostrar la notificación
        createChannel()

        // ---- ACCIONES ----

        // 1️⃣ Ver mi Perfil
        val perfilIntent = Intent(context, MainActivity::class.java)
        val perfilPendingIntent = PendingIntent.getActivity(
            context, 0, perfilIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 2️⃣ Follow / Unfollow
        val followIntent = Intent(context, FollowReceiver::class.java).apply {
            putExtra("userId", userId)
        }
        val followPendingIntent = PendingIntent.getBroadcast(
            context, 1, followIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 3️⃣ Ver Usuario
        val verUsuarioIntent = Intent(context, UserPhotosActivity::class.java).apply {
            putExtra("userId", userId)
        }
        val verUsuarioPendingIntent = PendingIntent.getActivity(
            context, 2, verUsuarioIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // ---- NOTIFICACIÓN ----

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)  // Ícono seguro
            .setContentTitle("Nueva interacción")
            .setContentText("Un usuario calificó tu foto")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(
                R.mipmap.ic_launcher,
                "Ver mi Perfil",
                perfilPendingIntent
            )
            .addAction(
                R.mipmap.ic_launcher,
                "Follow / Unfollow",
                followPendingIntent
            )
            .addAction(
                R.mipmap.ic_launcher,
                "Ver Usuario",
                verUsuarioPendingIntent
            )
            .build()

        NotificationManagerCompat.from(context).notify(1001, notification)
    }

    private fun createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Wear Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}