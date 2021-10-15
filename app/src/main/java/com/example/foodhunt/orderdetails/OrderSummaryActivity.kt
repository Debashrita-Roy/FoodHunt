package com.example.foodhunt.orderdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.foodhunt.R
import com.example.foodhunt.database.DBWrapper
import com.example.foodhunt.paymentdetails.PaymentActivity

data class Order(val itemName : String? = null, val itemPrice :String? = null, val itemCount : String? = null)

class OrderSummaryActivity : AppCompatActivity() {

    lateinit var itemnTextView: TextView
    lateinit var amountTextView: TextView
    lateinit var priceTextView: TextView
    lateinit var countTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        itemnTextView = findViewById(R.id.itemT)
        amountTextView = findViewById(R.id.amountT)
        priceTextView = findViewById(R.id.priceT)
        countTextView = findViewById(R.id.countT)

        val g = intent.extras
        val iname = g?.getString("iname")
        val iprice = g?.getString("iprice")
        val icount = g?.getInt("icount")

        val price = icount?.let { iprice?.toInt()?.times(it) }

        Log.d("OrderSummaryActivity","$iname,$iprice,$icount")

        itemnTextView.setText("$iname")
        priceTextView.setText("$iprice")
        amountTextView.setText("$price")
        countTextView.setText("$icount")

    }

    fun confirmClick(view: View) {

        val g = intent.extras
        val iname = g?.getString("iname")
        val iprice = g?.getString("iprice")
        val icount = g?.getInt("icount")

        val order = Order(iname.toString(),iprice.toString(), icount.toString())

        //
        val b = Bundle()
        b.putString("iname", iname)
        b.putString("iprice", iprice)
        b.putInt("icount", icount!!)
        //

        val wrapper = DBWrapper(this)
        val rowid = wrapper.addOrder(order)
        if (rowid.toInt() != -1) {
            Toast.makeText(this, "Order Details saved", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PaymentActivity::class.java)
            i.putExtras(b)
            startActivity(i)
        } else
            Toast.makeText(this, "Error saving details..", Toast.LENGTH_SHORT).show()

    }
}