package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhunt.authentication.SignInActivity
import com.example.foodhunt.hoteldata.Hotel
import com.example.foodhunt.hoteldata.HotelAdapter
import com.example.foodhunt.itemdata.AllItemActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DisplayItemActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    var hotelList = mutableListOf<Hotel>()
    lateinit var rView: RecyclerView

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

                R.id.nav_location -> {
                    Toast.makeText(applicationContext, "Clicked location", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(this, ShowNearbyHotelMap::class.java)
                    startActivity(i)

                }
                R.id.nav_offer -> {
                    Toast.makeText(applicationContext, "Clicked offers", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,FoodHuntGold::class.java)
                    startActivity(i)
                }
                R.id.nav_logout -> {

                    val userDetails = getSharedPreferences("Credentials", MODE_PRIVATE)
                    if (userDetails.all.isEmpty()) {
                        val i = Intent(this, SignInActivity::class.java)
                        startActivity(i)
                        Toast.makeText(this, "Already Logged out", Toast.LENGTH_SHORT).show()

                    }
                    else {
                        val editor = userDetails.edit()
                        editor.clear()
                        editor.commit()
                        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, SignInActivity::class.java)
                        startActivity(i)

                    }

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
      val i = Intent(this, SignInActivity::class.java)
    }



}

