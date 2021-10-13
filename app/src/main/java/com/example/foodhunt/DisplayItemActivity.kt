package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DisplayItemActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item)

        val drawerLayout: DrawerLayout =findViewById(R.id.drawerLayout)
        val navView: NavigationView =findViewById(R.id.nav_view)

        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home-> {
                    Toast.makeText(applicationContext, "Clicked home", Toast.LENGTH_SHORT).show()
                    val i= Intent(this, DisplayItemActivity::class.java)
                    startActivity(i)
                }R.id.nav_cart-> {
                Toast.makeText(applicationContext, "Clicked cart", Toast.LENGTH_SHORT).show()
                val i= Intent(this, AllItemActivity::class.java)
                startActivity(i)
            }R.id.nav_location-> {
                Toast.makeText(applicationContext, "Clicked location", Toast.LENGTH_SHORT).show()
                val i= Intent(this, AllHotelActivity::class.java)
                startActivity(i)
            }R.id.nav_offer-> {
                Toast.makeText(applicationContext, "Clicked offers", Toast.LENGTH_SHORT).show()
                val i= Intent(this, DisplayItemActivity::class.java)
                startActivity(i)
            }
                R.id.nav_logout->{
                    Toast.makeText(applicationContext, "Please sign-in again", Toast.LENGTH_SHORT).show()
                    val i= Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}