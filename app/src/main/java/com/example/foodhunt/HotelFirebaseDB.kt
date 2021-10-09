package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class HotelFirebaseDB : AppCompatActivity(), View.OnClickListener {

    lateinit var db: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var nameEditText: EditText
    lateinit var descEditText: EditText
    lateinit var addHotelButton: Button
    lateinit var image: ImageView

    companion object{
        lateinit var b : Bundle
        lateinit var b1 : Bundle
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_firebase_db)
         supportActionBar?.hide()

        nameEditText = findViewById(R.id.hotNameE)
        descEditText = findViewById(R.id.descE)


        addHotelButton = findViewById(R.id.regB)


        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

    //    addPredefinedHotels()
        addCreateFirebase()
    }


    private fun addHotel() {

        val name = nameEditText.text.toString()
        val desc = descEditText.text.toString()

        val hotOwner = Hotel(101, name, desc)
        val hotRef = db.getReference("Hotels/Hotel Name")
        hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)

    }

    fun addClick(view: View) {
        addHotel()

        nameEditText.setText("")
        descEditText.setText("")

//        val i = Intent(this, SignInActivity::class.java)
//        startActivity(i)
    }

    fun getMaps(view: View) {

         val i = Intent(this,DisplayActivity::class.java)
         i.putExtras(b)
         startActivity(i)

    }

    override fun onClick(v: View?) {

    }


    fun submitClick(view: View) {}

}


