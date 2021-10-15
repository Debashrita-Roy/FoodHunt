package com.example.foodhunt.paymentdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.foodhunt.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [UPI.newInstance] factory method to
 * create an instance of this fragment.
 */
class UPI : Fragment() {

    lateinit var gpayButton: Button
    lateinit var phonepeButton: Button
    lateinit var paytmButton: Button

    lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_u_p_i, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        gpayButton=view.findViewById(R.id.gPayRB)
        phonepeButton=view.findViewById(R.id.phonepeRB)
        paytmButton=view.findViewById(R.id.paytmRB)

        orderButton=view.findViewById(R.id.orderB)

        orderButton.setOnClickListener {
            successpay()
        }


        gpayButton.setOnClickListener{
            Toast.makeText(activity,"GooglePay Payment processing",Toast.LENGTH_LONG).show()
        }
        phonepeButton.setOnClickListener{
            Toast.makeText(activity,"Phonepe Payment processing",Toast.LENGTH_LONG).show()
        }
        paytmButton.setOnClickListener{
            Toast.makeText(activity,"Paytm Payment processing",Toast.LENGTH_LONG).show()
        }
    }

    fun successpay() {
            val frag= CashOnDelivery()
            childFragmentManager.beginTransaction().replace(R.id.frameLayout2,frag).commit()
            orderButton.isVisible=false
    }

}