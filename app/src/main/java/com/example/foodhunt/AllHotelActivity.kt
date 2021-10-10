package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        getHotelData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(" Show on Map ")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            " Show on Map " -> {
                val mapfrag = MapsFragment()
                supportFragmentManager.beginTransaction().add(R.id.containerL,mapfrag).commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getHotelData() {
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                        getItemsData(hot)
                    }
                    rView.adapter = HotelAdapter(hotelList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getItemsData( hot : Hotel){
        val hotDescRef = db.getReference("Hotels").child("Hotel Name").child("${hot.hotelName}").child("Items")
        hotDescRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val items = i.getValue(Item::class.java)
                        itemList.add(items!!)
                    }
                    Log.d("AllHotelActivity", "$itemList")
//                    rView.adapter = ItemAdapter(itemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


//    fun setUpRecyclerView() {
//        hotelAdapter = HotelAdapter(hotelList)
//        rView.adapter = hotelAdapter
//        rView.layoutManager = GridLayoutManager(this,2)
//    }


}