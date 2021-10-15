package com.example.foodhunt.paymentdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.foodhunt.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PayNow.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardPay : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var cardnumberEditText: EditText
    lateinit var expireDateEditText: EditText
    lateinit var cardnameEditText: EditText
    lateinit var cvvEditText: EditText
    lateinit var orderButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cardnumberEditText=view.findViewById(R.id.cardNoE)
        expireDateEditText=view.findViewById(R.id.expDateE)
        cardnameEditText=view.findViewById(R.id.cnameE)
        cvvEditText=view.findViewById(R.id.cvvE)
        orderButton=view.findViewById(R.id.porderB)

        orderButton.setOnClickListener{
            placeOrderClick()
        }
    }
    fun placeOrder() {
        val frag= CashOnDelivery()
        childFragmentManager.beginTransaction().replace(R.id.ConstraintLayout2,frag).commit()
        orderButton.isVisible=false

    }

    fun placeOrderClick() {
        val cardNo = cardnumberEditText.text.toString()
        val expireDate = expireDateEditText.text.toString()
        val cardName = cardnameEditText.text.toString()
        val cvv = cvvEditText.text.toString()

        if (cardNo.isNotEmpty() && expireDate.isNotEmpty() && cardName.isNotEmpty() && cvv.isNotEmpty()) {

            when {
                cardNo.isEmpty() -> cardnumberEditText.setError("Please enter card Number")
                cardNo.length != 19 -> cardnumberEditText.setError("Please enter correct card number")
                expireDate.isEmpty() -> expireDateEditText.setError("Please enter expire date")
                cardName.isEmpty() -> cardnameEditText.setError("Please enter name")
                cvv.isEmpty() -> cvvEditText.setError("Please enter cvv")

                else -> {
                    Toast.makeText(activity, "Payment Successful", Toast.LENGTH_SHORT).show()
                    placeOrder()
                }
            }
        }
    }
}