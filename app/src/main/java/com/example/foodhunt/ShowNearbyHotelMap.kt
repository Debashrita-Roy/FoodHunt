package com.example.foodhunt

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.constraintlayout.motion.widget.Debug.getLocation2
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_show_nearby_hotel_map.*

class ShowNearbyHotelMap : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_nearby_hotel_map)

        supportActionBar?.setTitle("Hotels Near By")
            val mapfrag = MapsFragment()
            supportFragmentManager.beginTransaction().add(R.id.nearbymapsL, mapfrag).commit()

        }
    }



