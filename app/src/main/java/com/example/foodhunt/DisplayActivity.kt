package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

//    lateinit var mapButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

//        mapButton = findViewById(R.id.showMap)


        val i = intent.extras

//        val mapfrag = MapsFragment()
//        supportFragmentManager.beginTransaction().add(R.id.parentL,mapfrag).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Get Hotel Details")
        menu?.add("Search Hotel on Maps")
        menu?.add("All Hotels")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Get Hotel Details" -> getHotelDetails("Customize")
            "Search Hotel on Maps" -> {
                val mapfrag = MapsFragment()
                supportFragmentManager.beginTransaction().add(R.id.parentL,mapfrag).commit()
            }
            "All Hotels" -> {
                val i = Intent(this, AllHotelActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}