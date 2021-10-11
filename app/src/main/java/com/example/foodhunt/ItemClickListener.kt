package com.example.foodhunt

interface ItemClickListener {
    fun addCount(item :Item, position:Int,count :Int)
    fun subtractCount(item :Item, position:Int,count :Int)
    fun callActivity(itemname : String,itemprice: String,itemcount :Int)
}