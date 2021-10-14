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

        addCreateFirebase()
        getHotelData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(" Show on Map ")
        menu?.add("Show all Items")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            " Show on Map " -> {
                val mapfrag = MapsFragment()
                supportFragmentManager.beginTransaction().add(R.id.containerL,mapfrag).commit()
            }
            "Show all Items" ->{
                val i  = Intent(this, AllItemActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
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