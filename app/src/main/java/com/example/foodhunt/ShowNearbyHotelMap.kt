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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_show_nearby_hotel_map.*

class ShowNearbyHotelMap : AppCompatActivity() {

    lateinit var fusedLocationProvider : FusedLocationProviderClient
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_nearby_hotel_map)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

        supportActionBar?.setTitle("Hotels Near By")
//            val mapfrag = MapsFragment()
//            supportFragmentManager.beginTransaction().add(R.id.nearbymapsL, mapfrag).commit()
    }

    private fun fetchLocation() {

        val task = fusedLocationProvider.lastLocation
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 101
            )
        }

        task.addOnSuccessListener {
            if(it!=null){
                val userlat = it.latitude
                val userlong = it.longitude
                val b = Bundle()
                b.putDouble("latitude", userlat)
                b.putDouble("longitude", userlong)
                Toast.makeText(applicationContext, "${it.latitude} , ${it.longitude}", Toast.LENGTH_SHORT).show()
                val mapfrag = MapsFragment()
                mapfrag.arguments = b
                supportFragmentManager.beginTransaction().add(R.id.nearbymapsL, mapfrag).commit()
            }
        }
    }

}




