package com.example.foodhunt

import android.view.autofill.AutofillId

data class Hotel(val registerId : Int,
                 val hotelName: String? = null,
                 val menuItem1 : String? = null,
                 val menuItem2 : String? = null,
                 val menuItem3 : String? = null,
                 val menuItem4 : String? = null,
)