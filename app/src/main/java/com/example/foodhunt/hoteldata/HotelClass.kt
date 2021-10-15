package com.example.foodhunt.hoteldata

data class Hotel(
    val registerId: Int = 0,
    val hotelName: String? = null,
    val description : String? = null,
    val address : String? = null,
    val latitude : Double = 0.0,
    val longitude : Double = 0.0,
    var hotelImageURL : String? = null
)

// for items
data class Item(
    val itemName : String? = null,
    val price : Int = 0,
    var count : Int = 0,
    val itemImage : String? = null,
)



