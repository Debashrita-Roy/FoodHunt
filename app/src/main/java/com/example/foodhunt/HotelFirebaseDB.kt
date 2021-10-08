package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HotelFirebaseDB : AppCompatActivity(), View.OnClickListener {

    lateinit var db: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var nameEditText: EditText
    lateinit var item1EditText: EditText
    lateinit var item2EditText: EditText
    lateinit var item3EditText: EditText
    lateinit var item4EditText: EditText
    lateinit var addButton: Button
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
        item1EditText = findViewById(R.id.Item1E)
        item2EditText = findViewById(R.id.Item2E)
        item3EditText = findViewById(R.id.Item3E)
        item4EditText = findViewById(R.id.Item4E)
        addButton = findViewById(R.id.regB)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        addPredefinedHotels()
    }


    private fun addHotel() {

        val name = nameEditText.text.toString()
        val item1 = item1EditText.text.toString()
        val item2 = item2EditText.text.toString()
        val item3 = item3EditText.text.toString()
        val item4 = item4EditText.text.toString()

        val hotOwner = Hotel( 105,name, item1, item2, item3, item4)
        val hotRef = db.getReference("All Hotels/Hotel Name-")
        hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)

    }

    fun addClick(view: View) {
        addHotel()

        nameEditText.setText("")
        item1EditText.setText("")
        item2EditText.setText("")
        item3EditText.setText("")
        item4EditText.setText("")

        val i = Intent(this, SignInActivity::class.java)
        startActivity(i)
    }

    fun getMaps(view: View) {

         val i = Intent(this,DisplayActivity::class.java)
         i.putExtras(b)
         startActivity(i)

    }

    override fun onClick(v: View?) {

    }
}


