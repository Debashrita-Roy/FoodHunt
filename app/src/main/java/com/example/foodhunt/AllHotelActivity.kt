package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllHotelActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView
    var hotelList = mutableListOf<Hotel>()
    var itemList = mutableListOf<Item>()

    lateinit var db : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hotel)

        rView = findViewById(R.id.hotelRV)
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(this)

        hotelList = arrayListOf<Hotel>()
        itemList = arrayListOf<Item>()

        //addCreateFirebase()
        getHotelData()

    }

    fun hotelSelectionDone(h : Hotel){
        Toast.makeText(this, "Selected Hotel: ${h.hotelName}",
            Toast.LENGTH_LONG).show()


    }

    fun setupRecyclerview(){
        rView.adapter = HotelAdapter(hotelList,::hotelSelectionDone)
    }



    fun getHotelData() {
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)

                    }
                    //rView.adapter = HotelAdapter(hotelList,::)
                    setupRecyclerview()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}