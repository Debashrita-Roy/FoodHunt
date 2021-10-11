package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class OrderSummaryActivity : AppCompatActivity() {

    lateinit var itemnTextView: TextView
    lateinit var amountTextView: TextView
    lateinit var priceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        itemnTextView = findViewById(R.id.itemT)
        amountTextView = findViewById(R.id.amountT)
        priceTextView = findViewById(R.id.priceT)

        val g = intent.extras
        val iname = g?.getString("iname")
        val iprice = g?.getString("iprice")
        val icount = g?.getInt("icount")

        val price = icount?.let { iprice?.toInt()?.times(it) }

        Log.d("OrderSummaryActivity","$iname,$iprice,$icount")

        itemnTextView.setText("$iname - $icount")
        priceTextView.setText("$iprice")
        amountTextView.setText("$price")


    }
}