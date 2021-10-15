package com.example.foodhunt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class FoodHuntGold: AppCompatActivity() {

    lateinit var claimButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_hunt_gold)
        supportActionBar?.hide()
        claimButton=findViewById(R.id.claimB)
    }

    fun claimClick(view: View) {
        Toast.makeText(this,"Congratulations!!! You claimed your offer",Toast.LENGTH_LONG).show()
    }

}