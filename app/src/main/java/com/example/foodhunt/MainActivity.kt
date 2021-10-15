package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.foodhunt.authentication.SignInActivity
import com.example.foodhunt.hoteldata.HotelFirebaseDB

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var registerAButton: Button
    lateinit var registerUButton: Button
    lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        registerAButton = findViewById(R.id.registerAdminB)
        registerUButton = findViewById(R.id.registeruserB)


        signInButton = findViewById(R.id.signinB)
        signInButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signinB -> {
                val i = Intent(this, SignInActivity::class.java)
                startActivity(i)
            }
            R.id.registeruserB -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            }
            R.id.registerAdminB -> {
                val i = Intent(this, HotelFirebaseDB::class.java)
                startActivity(i)
            }
        }
    }
}

