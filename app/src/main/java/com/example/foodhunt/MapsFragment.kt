package com.example.foodhunt

import com.example.foodhunt.database.db
import com.example.foodhunt.hoteldata.Hotel

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.android.gms.maps.CameraUpdate

import com.google.android.gms.maps.model.CameraPosition


class MapsFragment : Fragment() {

    var hotelList = mutableListOf<Hotel>()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
/////////pre defined hotels added to list

        val bundle = arguments
        val lat = bundle?.getDouble("latitude")
        val long = bundle?.getDouble("longitude")
        Log.d("MapsFragment", "lat : $lat, long : $long")




//////////////////////////////////////// Code to display all Hotels /////////////////////////////////////////

//        db = FirebaseDatabase.getInstance()
//        val hotRef = db.getReference("Hotels").child("Hotel Name")
//
//        hotRef.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (i in snapshot.children) {
//                        val hot = i.getValue(Hotel::class.java)
//                        hotelList.add(hot!!)
//                        val res1Loc = LatLng(hot.latitude, hot.longitude)
//                        val res1 = googleMap.addMarker(
//                            MarkerOptions().position(res1Loc).title("${hot.hotelName}")
//                        )
//                        googleMap.setOnInfoWindowClickListener() {
//                            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_LONG)
//                                .show()
//
//                        }
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(res1Loc))
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//
//    }


//////////////////////////////////////// Code to display nearby Hotels /////////////////////////////////////////
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                        val res1Loc = LatLng(hot.latitude, hot.longitude)

                        val userLocation = Location("myLocation")
                        userLocation.latitude = lat!!
                        userLocation.longitude = long!!

                        val hotelLocation = Location("hotelNearMe")
                        hotelLocation.latitude = hot.latitude
                        hotelLocation.longitude = hot.longitude
                        val distance = userLocation.distanceTo(hotelLocation)
                        Log.d("MapsFragment", "distance : $distance")

                        if (distance < 100000) {
                            val res1 = googleMap.addMarker(
                                MarkerOptions().position(res1Loc).title("${hot.hotelName}")
                            )
                            googleMap.setOnInfoWindowClickListener() {
                                Toast.makeText(
                                    activity,
                                    "See items in ${it.title}",
                                    Toast.LENGTH_LONG
                                ).show()
                                val i = Intent(activity,DisplayItemActivity::class.java)
                                startActivity(i)

                            }
                            val cameraPosition =
                                CameraPosition.Builder().target(res1Loc).zoom(10.0f).build()
                            val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                            googleMap.moveCamera(cameraUpdate)
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(res1Loc))
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

}


