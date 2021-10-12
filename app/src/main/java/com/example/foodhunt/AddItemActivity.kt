package com.example.foodhunt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class AddItemActivity : AppCompatActivity() {

    lateinit var itemNameEditText: EditText
    lateinit var itemPriceEditText: EditText
    lateinit var addItemButton: Button
    var itemList = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        supportActionBar?.hide()

        itemNameEditText = findViewById(R.id.itemNameE)
        itemPriceEditText = findViewById(R.id.itemPriceE)
        addItemButton = findViewById(R.id.addItemB)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

    //    itemList = arrayListOf<Item>()

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menu?.add("Show list")
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.title) {
//            "Show list" -> {
//                val itemDetails = getItemsData()
//                val itemBundle = Bundle()
//                itemBundle.putString("itemDetails", "$itemDetails")
//
//                val i = Intent(this, AllHotelActivity::class.java)
//                i.putExtras(itemBundle)
//                startActivity(i)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    fun addItem(view: View) {

        val i = intent.extras
        val hotName = i?.getString("Hotelname")

        Log.d("AddItemActivity", "Hotel name : $hotName")
        val iname = itemNameEditText.text.toString()
        val iprice = itemPriceEditText.text.toString()

        val item = Item(iname,iprice.toInt(),10)

        val itemRef = db.getReference("Hotels/Hotel Name/$hotName/Items")
        itemRef.child(item.itemName.toString()).setValue(item)
        Toast.makeText(this, "$iname added successfully in Hotel $hotName", Toast.LENGTH_SHORT)
            .show()
        itemNameEditText.setText("")
        itemPriceEditText.setText("")

    }

   fun getItemsData() : MutableList<Item>{

        val i = intent.extras
        val hotName = i?.getString("Hotelname")

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val hotDescRef =
            db.getReference("Hotels").child("Hotel Name").child("$hotName").child("Items")
        hotDescRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snap: DataSnapshot) {
                if (snap.exists()) {
                    for (i in snap.children) {
                        val items = i.getValue(Item::class.java)
                        itemList.add(items!!)
                    }

                    Log.d("AddItemActivity", "$itemList")

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return itemList
    }

    fun showHotel(view: View) {
        val itemDetails = getItemsData()
        val itemBundle = Bundle()
        itemBundle.putString("itemDetails", "$itemDetails")

        val i = Intent(this, AllHotelActivity::class.java)
        i.putExtras(itemBundle)
        startActivity(i)
    }
}