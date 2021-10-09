package com.example.foodhunt

import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

lateinit var db: FirebaseDatabase
lateinit var auth: FirebaseAuth
//
//fun addPredefinedHotels() {
//
////    lateinit var db: FirebaseDatabase
////    lateinit var auth: FirebaseAuth
//
//    db = FirebaseDatabase.getInstance()
//    auth = FirebaseAuth.getInstance()
//
////    val hot1 = Hotel(101, "Customize", items = ("Pizza",100)
////    )
////
////    val hot2 = Hotel(
////        102, "StreetStyle",
////        "Chat", "Panipuri", "Shevpuri", "Ragada"
////    )
////
////    val hot3 = Hotel(
////        103, "Customize",
////        "Sandwich", "Maggie", "Pizza", "Burrito"
////    )
////
////    val hot4 = Hotel(
////        104, "Maharashtra Darbar",
////        "Momos", "Burrito", "Franky", "Sticks"
////    )
//
//    val hotRef = db.getReference("All Hotels/Hotel Name-/Sample")
//
////
////    hotRef.child(hot1.hotelName.toString()).setValue(hot1)
////    hotRef.child(hot2.hotelName.toString()).setValue(hot2)
////    hotRef.child(hot3.hotelName.toString()).setValue(hot3)
////    hotRef.child(hot4.hotelName.toString()).setValue(hot4)
////
////    HotelFirebaseDB.b = Bundle()
////    HotelFirebaseDB.b.putString("hotelref",hot1.toString())
//
//
//}


fun addCreateFirebase(){
    db = FirebaseDatabase.getInstance()
    auth = FirebaseAuth.getInstance()

    val hot1 = Hotel(101, "Zayka", "desc" )
    val hot2 = Hotel(102, "Darbar", "desc" )

    val hotRef = db.getReference("Hotels/Hotel Name")
    hotRef.child(hot1.hotelName.toString()).setValue(hot1)

    hotRef.child(hot2.hotelName.toString()).setValue(hot2)

    val item1 = Item("Pizza",10,10)
    val item2 = Item("abc",10,10)

    val itemRef = db.getReference("Hotels/Hotel Name/${hot1.hotelName.toString()}/Items")
    itemRef.child(item1.itemName.toString()).setValue(item1)
    itemRef.child(item2.itemName.toString()).setValue(item2)

    val itemRef2 = db.getReference("Hotels/Hotel Name/${hot2.hotelName.toString()}/Items")
    itemRef2.child(item1.itemName.toString()).setValue(item1)
    itemRef2.child(item2.itemName.toString()).setValue(item2)

}


fun allHotelDetails() {
    val hotRef = db.getReference("All Hotels")
        .child("Hotel Name-")

    hotRef.addValueEventListener(object : ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            Log.d("HotelFirebaseDB","Got Hotel : $snapshot")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
}

fun getHotelDetails(name : String) {

    val hotRef = db.getReference("All Hotels")
        .child("Hotel Name-")
        .child(name)

    hotRef.addValueEventListener(object : ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            val namee = snapshot.child("hotelName").getValue()
//            val hotel = snapshot.getValue(Hotel::class.java)
            Log.d("HotelFirebaseDB", "Got Hotel : $namee")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })

}
