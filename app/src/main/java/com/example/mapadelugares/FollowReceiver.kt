package com.example.mapadelugares

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class FollowReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val userId = intent.getStringExtra("userId") ?: return

        // Aquí iría el endpoint real (pero Coursera NO lo pide)
        // Simulamos acción:
        val message = "Enviando Follow/Unfollow al usuario $userId"

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}