package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AddItemActivity : AppCompatActivity() {

    lateinit var itemNameEditText: EditText
    lateinit var itemPriceEditText: EditText
    lateinit var addItemButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)


        itemNameEditText = findViewById(R.id.itemNameE)
        itemPriceEditText = findViewById(R.id.itemPriceE)
        addItemButton = findViewById(R.id.itemB)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun addItem(view: View) {

        val i = intent.extras
        val hotName = i?.getString("Hotelname")

        Log.d("AddItemActivity","Hotel name : $hotName")
        val iname = itemNameEditText.text.toString()
        val iprice = itemPriceEditText.text.toString()

        val item = Item(iname,iprice.toInt(),10)

        val itemRef = db.getReference("Hotels/Hotel Name/$hotName/Items")
        itemRef.child(item.itemName.toString()).setValue(item)
        Toast.makeText(this, "$iname added successfully in Hotel $hotName", Toast.LENGTH_SHORT).show()
         itemNameEditText.setText("")
         itemPriceEditText.setText("")
    }
}