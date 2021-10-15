package com.example.foodhunt

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.all_hotel_list_item.*
import java.io.InputStream
import javax.security.auth.callback.Callback
import kotlin.system.exitProcess

class DisplayItemActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    var hotelList = mutableListOf<Hotel>()
    lateinit var rView: RecyclerView
    lateinit var preferences: SharedPreferences
    lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(applicationContext, "Clicked home", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, DisplayItemActivity::class.java)
                    startActivity(i)
                }
                R.id.nav_cart -> {
                    Toast.makeText(applicationContext, "Clicked cart", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, AllItemActivity::class.java)
                    startActivity(i)
                }
                R.id.nav_location -> {
                    Toast.makeText(applicationContext, "Clicked location", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(this, ShowNearbyHotelMap::class.java)
                    startActivity(i)

                }
                R.id.nav_offer -> {
                    Toast.makeText(applicationContext, "Clicked offers", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,foodhuntgold::class.java)
                    startActivity(i)
                }
                R.id.nav_logout -> {

                    val userdetails: SharedPreferences.Editor = preferences.edit()
                    userdetails.clear()
                    userdetails.apply()

                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Toast.makeText(applicationContext, "Logged out", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }
        rView = findViewById(R.id.RV)
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(this)
        db = FirebaseDatabase.getInstance()
      //  addCreateFirebase()
        getHotelData()
    }
    fun hotelSelectionDone(h : Hotel){
        Toast.makeText(this, "Selected Hotel: ${h.hotelName}",
            Toast.LENGTH_LONG).show()
        val i = Intent(this, AllItemActivity::class.java)
        startActivity(i)

    }

    fun setupRecyclerview(){
        rView.adapter = HotelAdapter(hotelList,::hotelSelectionDone)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun getHotelData() {
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                        // getItemsData(hot)
                    }
                    //rView.adapter = HotelAdapter(hotelList)
                    setupRecyclerview()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onBackPressed() {
        finish();
        System.exit(0)
    }



}

