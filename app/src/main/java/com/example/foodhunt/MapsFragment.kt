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
import java.util.ArrayList

class MapsFragment : Fragment() {

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
        //============================================================================
        val res1Loc =  LatLng(19.0205, 72.87)
        val res1 = googleMap.addMarker(MarkerOptions().position(res1Loc).title("Zaika Restaurant"))
        googleMap.setOnInfoWindowClickListener() {
            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_LONG).show()

        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(res1Loc))
//===============================================================================================
        val res2Loc = LatLng(19.220, 72.85)
        val res2 = googleMap.addMarker(MarkerOptions().position(res2Loc).title("Modern Pride Family Restaurent").snippet("This is a comfortable and cozy place"))
        googleMap.setOnInfoWindowClickListener() {
            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_SHORT).show()
//            val flag = 1
//            val b = Bundle()
//            b.putString("res2",res2.title.toString())
//            b.putString("flag",flag.toString())
//
//            val i = Intent(activity,RestaurentActivity::class.java)
//            i.putExtras(b)
//            startActivity(i)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(res2Loc))
//================================================================================================================
        val cafe1Loc = LatLng(19.103, 72.86)
        val cafe1 = googleMap.addMarker(MarkerOptions().position(cafe1Loc).title("Grandma's Cafeteria"))

        googleMap.setOnInfoWindowClickListener() {
            Toast.makeText(activity, "Clicked on ${it.title}", Toast.LENGTH_SHORT).show()

//            val b = Bundle()
//            b.putString("cafe1",cafe1.title.toString())
//
//            val i = Intent(activity,RestaurentActivity::class.java)
//            i.putExtras(b)
//            startActivity(i)

        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cafe1Loc))
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

class Items(val item1 : String, val item2 : String) {
}


