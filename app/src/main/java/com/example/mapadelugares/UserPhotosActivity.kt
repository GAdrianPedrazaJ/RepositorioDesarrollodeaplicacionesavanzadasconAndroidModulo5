package com.example.mapadelugares

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class UserPhotosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_photos)

        // Obtener el userId (solo para que no se pierda la lógica)
        val userId = intent.getStringExtra("userId")

        // Mostrar las imágenes falsas de ejemplo
        val img1 = findViewById<ImageView>(R.id.img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        val img3 = findViewById<ImageView>(R.id.img3)

        img1.setImageResource(R.drawable.foto1)
        img2.setImageResource(R.drawable.foto2)
        img3.setImageResource(R.drawable.foto3)
    }
}