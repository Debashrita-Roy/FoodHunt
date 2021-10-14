package com.example.foodhunt

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import com.example.foodhunt.R
import kotlinx.android.synthetic.main.fragment_cash_on_delivery.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [PayOnDelivery.newInstance] factory method to
 * create an instance of this fragment.
 */
class CashOnDelivery : Fragment() {

    lateinit var backButton: Button
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_on_delivery, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton=view.findViewById(R.id.backB)



        backButton.setOnClickListener{
            placeOrder()
        }

    }

    /*fun placeOrder() {
        val frag= CashOnDelivery()
        childFragmentManager.beginTransaction().replace(R.id.ConstraintLayout2,frag).commit()
        backButton.isVisible=false

    }*/

    fun placeOrder(){
        val intent = Intent(activity, DisplayItemActivity::class.java)
        startActivity(intent)

    }
    }


