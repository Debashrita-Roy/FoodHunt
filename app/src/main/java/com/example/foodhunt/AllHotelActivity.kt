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

    var hotelList = mutableListOf<Hotel>()
 //   var itemList = mutableListOf<String>()

    lateinit var db : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hotel)

        rView = findViewById(R.id.hotelRV)
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(this)

        hotelList = arrayListOf<Hotel>()

        getUsesData()
//        db = FirebaseDatabase.getInstance()
//
//        val hotRef = db.getReference("Hotels")
//            .child("Hotel Name")
//
//        hotRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("AllHotelActivity", "\nGot Hotel : $snapshot")
//                val hotelCount = snapshot.childrenCount
//                for (i in 0..hotelCount - 1) {
//                    val hotel1 = snapshot.children.elementAt(i.toInt())
//                    hotelList.add(hotel1.child("hotelName").value.toString())
//                    Log.d("AllHotelActivity", "\nHotels are  : $hotel1\n")
//                }
//                Log.d("AllHotelActivity","hotelList : $hotelList")
//               for(j in 0..(hotelList.size - 1)){
//                   val hotel1 = snapshot.children.elementAt(j.toInt())
//                   val hotname = hotelList[j]
//                   Log.d("AllHotelActivity","hotname : $hotname")
//                   itemList.add(hotel1.child("hotelName/$hotname/Items").value.toString())
//               }
//                Log.d("AllHotelActivity","hotelList : $itemList")
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })

//        setUpRecyclerView()

    }

    private fun getUsesData() {
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (usersnapshot in snapshot.children){
                        val hot = usersnapshot.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                    }
                    rView.adapter = HotelAdapter(hotelList)
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

    private fun onHotelSelected(selectedHotel : Hotel){
        Toast.makeText(this, "$selectedHotel", Toast.LENGTH_SHORT).show()
    }

}