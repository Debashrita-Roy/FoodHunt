package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        getHotelData()

       // populateList()
        //rView.adapter = HotelAdapter(hotelList)
    }



//////////////////////////////////trying beacuse net nahiyeee

// fun populateList() {
//        hotelList.add(Hotel(101, "Zayka", "desc" ))
//    }
/////////////////////////////////////////////////////////////////

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

    fun getHotelData() {
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

    fun getItemsData(hot: Hotel) {
        val hotDescRef =
            db.getReference("Hotels").child("Hotel Name").child("${hot.hotelName}").child("Items")
        hotDescRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snap: DataSnapshot) {
                if (snap.exists()) {
                    for (i in snap.children) {
                        val items = i.getValue(Item::class.java)
                        itemList.add(items!!)
                    }
                    Log.d("AllHotelActivity", "$itemList")

                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}