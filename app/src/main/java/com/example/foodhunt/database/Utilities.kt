package com.example.foodhunt.database

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

lateinit var db: FirebaseDatabase
lateinit var auth: FirebaseAuth

/***   Adding predefined Hotels in firebase database */
//fun addCreateFirebase(){
//    db = FirebaseDatabase.getInstance()
//    auth = FirebaseAuth.getInstance()
//
//    val hot1 = Hotel(101, "Zayka", "This is a comfortable and cozy place", "Mumbai", 19.2094, 73.0939,"helo" )
//    val hot2 = Hotel(102, "Darbar", "desc", "Dombivali",19.2180, 73.1221  )
//
//    val hotRef = db.getReference("Hotels/Hotel Name")
//    hotRef.child(hot1.hotelName.toString()).setValue(hot1)
//
//    hotRef.child(hot2.hotelName.toString()).setValue(hot2)
//
//    val item1 = Item("Pizza",10)
//    val item2 = Item("Paneer",10)
//    val item3 = Item("Bhurji",10)
//    val item4 = Item("Bhakari",10)
//
//    val itemRef = db.getReference("Hotels/Hotel Name/${hot1.hotelName.toString()}/Items")
//    itemRef.child(item1.itemName.toString()).setValue(item1)
//    itemRef.child(item2.itemName.toString()).setValue(item2)
//
//    val itemRef2 = db.getReference("Hotels/Hotel Name/${hot2.hotelName.toString()}/Items")
//    itemRef2.child(item3.itemName.toString()).setValue(item3)
//    itemRef2.child(item4.itemName.toString()).setValue(item4)
//
//}
