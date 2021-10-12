package com.example.foodhunt

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

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

        val cafe1Loc = LatLng(19.2180, 73.1221)
        val cafe1 = googleMap.addMarker(MarkerOptions().position(cafe1Loc).title("Darbar"))

        googleMap.setOnInfoWindowClickListener() {
            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_SHORT).show()
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cafe1Loc))


        val res2Loc = LatLng(19.2094, 73.0939)
        val res2 = googleMap.addMarker(
            MarkerOptions().position(res2Loc).title("Zayka")
                .snippet("This is a comfortable and cozy place")
        )
        googleMap.setOnInfoWindowClickListener() {
            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_SHORT).show()
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(res2Loc))



        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                        val res1Loc = LatLng(hot.latitude, hot.longitude)
                        val res1 = googleMap.addMarker(
                            MarkerOptions().position(res1Loc).title("${hot.hotelName}")
                        )
                        googleMap.setOnInfoWindowClickListener() {
                            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_LONG)
                                .show()

                        }
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(res1Loc))
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



