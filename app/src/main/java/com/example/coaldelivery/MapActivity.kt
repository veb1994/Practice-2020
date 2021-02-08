package com.example.coaldelivery

import android.app.Activity
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


class MapActivity : Activity() {

    private val MAPKIT_API_KEY = "ec73461a-e81d-455c-a8a8-647480a60124"
    private val TARGET_LOCATION = Point(53.723545, 91.440842)
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        // Создание MapView.
        setContentView(R.layout.activity_map)
        super.onCreate(savedInstanceState)
        mapView = findViewById<View>(R.id.mapview) as MapView
        // Перемещение камеры в центр Абакана.
        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }

}
