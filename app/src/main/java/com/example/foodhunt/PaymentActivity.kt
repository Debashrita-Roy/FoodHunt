package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class PaymentActivity : AppCompatActivity() {


    lateinit var cashOnDeliveryButton: Button
    lateinit var cardPaymentButton: Button
    lateinit var upiPaymentButton: Button
    lateinit var paymentTextView: TextView
    lateinit var paymentImageView: ImageView
    lateinit var editOrderButton: Button
    


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        cashOnDeliveryButton=findViewById(R.id.cashpayB)
        cardPaymentButton=findViewById(R.id.cardpayB)
        upiPaymentButton=findViewById(R.id.upiB)
        paymentTextView=findViewById(R.id.paymentT)
        paymentImageView=findViewById(R.id.payIV)
        editOrderButton=findViewById(R.id.editB)
    }

    fun buttonClick(view: View) {
        cashOnDeliveryButton.isVisible=false
        cardPaymentButton.isVisible=false
        upiPaymentButton.isVisible=false
        paymentTextView.isVisible=false
        paymentImageView.isVisible=false
        editOrderButton.isVisible=false

        when(view.id){
            R.id.cashpayB ->{
                // Toast.makeText(this,"Pay On Delivery clicked",Toast.LENGTH_LONG).show()
                val codFrag= CashOnDelivery()
                supportFragmentManager.beginTransaction().replace(R.id.parentL,codFrag).commit()
            }

            R.id.cardpayB ->{
                //Toast.makeText(this,"Pay Now clicked",Toast.LENGTH_LONG).show()
                val cardFrag= CardPay()
                supportFragmentManager.beginTransaction().replace(R.id.parentL,cardFrag).commit()
            }

            R.id.upiB->{
                val upiFrag= UPI()
                supportFragmentManager.beginTransaction().replace(R.id.parentL,upiFrag).commit()
            }

            R.id.editB->{
                //
                val g = intent.extras
                val iname = g?.getString("iname")
                val iprice = g?.getString("iprice")
                val icount = g?.getInt("icount")

                val b = Bundle()
                b.putString("iname", iname)
                b.putString("iprice", iprice)
                b.putInt("icount", icount!!)

                val i = Intent(this,EditOrderActivity::class.java)
                i.putExtras(b)
                startActivity(i)
                //

            }
        }
    }

}