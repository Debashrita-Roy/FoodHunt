package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class HotelFirebaseDB : AppCompatActivity(), View.OnClickListener {

    lateinit var db: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var nameEditText: EditText
    lateinit var descEditText: EditText
    lateinit var addHotelButton: Button
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_firebase_db)
         supportActionBar?.hide()

        nameEditText = findViewById(R.id.hotNameE)
        descEditText = findViewById(R.id.descE)


        addHotelButton = findViewById(R.id.addHotelB)


        db = FirebaseDatabase.getInstance()

        addCreateFirebase()  // predefined hotels have been added
    }


    fun addHotel(view: View) {  // adding hotels dynamically  // onClick for add hotel button

        val name = nameEditText.text.toString()
        val desc = descEditText.text.toString()
        if (name.isNotEmpty() && desc.isNotEmpty()) {
            val hotOwner = Hotel(101, name, desc)
            val hotRef = db.getReference("Hotels/Hotel Name")
            hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)

            val b = Bundle()
            b.putString("Hotelname",hotOwner.hotelName)

            val i = Intent(this,AddItemActivity::class.java)
            i.putExtras(b)
            startActivity(i)
        }
        else{
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    fun submitClick(view: View) {

    }

    override fun onClick(v: View?) {
    }

}


