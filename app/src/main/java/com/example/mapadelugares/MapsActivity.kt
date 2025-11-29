package com.example.mapadelugares

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapsActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var titulo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargar configuración OSMDroid
        Configuration.getInstance().setUserAgentValue(packageName)

        // Cargar layout
        setContentView(R.layout.activity_maps)

        // Recibir datos del Intent
        lat = intent.getDoubleExtra("lat", 0.0)
        lng = intent.getDoubleExtra("lng", 0.0)
        titulo = intent.getStringExtra("titulo") ?: "Ubicación"

        // Referencia al MapView
        map = findViewById(R.id.map)

        // Configurar mapa
        map.setTileSource(TileSourceFactory.MAPNIK)   // Estilo del mapa
        map.controller.setZoom(17.0)
        map.controller.setCenter(GeoPoint(lat, lng))

        // Crear marcador
        val marker = Marker(map)
        marker.position = GeoPoint(lat, lng)
        marker.title = titulo
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

        // Agregar marcador al mapa
        map.overlays.add(marker)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()  // Requerido por OSMDroid
    }

    override fun onPause() {
        super.onPause()
        map.onPause() // Requerido por OSMDroid
    }
}