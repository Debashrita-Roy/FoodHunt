package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.foodhunt.Database.DBWrapper

class EditOrderActivity : AppCompatActivity() {

    lateinit var itemnTextView: TextView
    lateinit var amountTextView: TextView
    lateinit var priceTextView: TextView
    lateinit var countEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_order)

        itemnTextView = findViewById(R.id.edititemT)
        amountTextView = findViewById(R.id.editamountT)
        priceTextView = findViewById(R.id.editpriceT)
        countEditText = findViewById(R.id.editCountE)

        val g = intent.extras
        val iname = g?.getString("iname")
        val iprice = g?.getString("iprice")
        val icount = g?.getInt("icount") ?: 0

        Log.d("EditOrderActivity","$iname $iprice $icount")

        itemnTextView.setText(iname)
        priceTextView.setText(iprice)
        countEditText.setText(icount.toString())
    }

    fun updateClick(view: View) {
        val iname = itemnTextView.text.toString()
        val iprice = priceTextView.text.toString()
        val icount = countEditText.text.toString()

        val amount = iprice.toInt().times(icount.toInt())

        val wrapper = DBWrapper(this)
        val order = Order(iname,iprice,icount)
        wrapper.editOrder(order)

        amountTextView.setText(amount.toString())
    }

    fun confirmClick(view: View) {
        val i = Intent(this, PaymentActivity::class.java)
        startActivity(i)
    }
}