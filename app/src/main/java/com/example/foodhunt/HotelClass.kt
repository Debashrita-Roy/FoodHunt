package com.example.foodhunt


//
//data class Hotel(val registerId : Int,
//                 val hotelName: String? = null,
//                 val menuItem1 : String? = null,
//                 val menuItem2 : String? = null,
//                 val menuItem3 : String? = null,
//                 val menuItem4 : String? = null,
//)

data class Hotel(
    val registerId: Int,
    val hotelName: String? = null,
//    val image : Int,
    val description : String
)

// for items
data class Item(
    val itemName : String,
    val price : Int,
    val itemImage : Int
)



