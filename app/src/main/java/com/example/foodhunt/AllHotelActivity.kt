package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class AllHotelActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView
    lateinit var hotelAdapter: HotelAdapter

    var hotelList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hotel)

        rView = findViewById(R.id.hotelRV)
        rView!!.setHasFixedSize(true)
        rView!!.layoutManager = LinearLayoutManager(this)

        hotelList = ArrayList<String>()

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val hotRef = db.getReference("All Hotels")
            .child("Hotel Name-")

        hotRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val namee = snapshot.child("hotelName").getValue()
            //    val l = snapshot.getValue(Hotel::class.java)
                hotelList.add(namee.toString())
//            val hotel = snapshot.getValue(Hotel::class.java)
                Log.d("HotelFirebaseDB", "Got Hotel : $namee")
                Log.d("AllHotelActivity","$hotelList")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        setUpRecyclerView()

    }

    fun setUpRecyclerView() {
        hotelAdapter = HotelAdapter(hotelList)
        rView.adapter = hotelAdapter
        rView.layoutManager = GridLayoutManager(this,2)
    }

    private fun onHotelSelected(selectedHotel : Hotel){
        Toast.makeText(this, "$selectedHotel", Toast.LENGTH_SHORT).show()
    }
}