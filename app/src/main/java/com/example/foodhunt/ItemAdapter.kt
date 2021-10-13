package com.example.foodhunt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.makeText
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private var itemName: MutableList<Item>,
                  private val itemClickListener: ItemClickListener)
    : RecyclerView.Adapter<ItemAdapter.ItemHolder>()  {

 inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
     val itemNameTextView = v.findViewById<TextView>(R.id.itemNameT)
     val itemPriceTextView = v.findViewById<TextView>(R.id.itemPriceT)
     var orderFoodButton = v.findViewById<Button>(R.id.orderFoodB)
     var addcountButton = v.findViewById<ImageButton>(R.id.addCount)
     var minuscountButton = v.findViewById<ImageButton>(R.id.minusCount)
     var itemcountTextView = v.findViewById<TextView>(R.id.odercountT)
     var itemImageView = v.findViewById<ImageView>(R.id.foodIV)


 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_items_in_hotel_list,parent,false)
        return ItemHolder(view)
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {


        val item = itemName[position]
        holder.itemNameTextView.text = item.itemName
        holder.itemPriceTextView.text = item.price.toString()
        holder.itemcountTextView.text = "${item.count}"

        Glide.with(holder.itemView)
            .load(item.itemImage)
            .into(holder.itemImageView)

        holder.itemView.setOnClickListener{
            Log.d("AdAdapter","Ad Clicked:$item")
        }

        var count = holder.itemcountTextView.text.toString()
        holder.addcountButton.setOnClickListener {
            itemClickListener.addCount(itemName[position],position,count.toInt())
        }
        holder.minuscountButton.setOnClickListener {
            itemClickListener.subtractCount(itemName[position],position,count.toInt())
        }
        holder.orderFoodButton.setOnClickListener {

            val iname = holder.itemNameTextView.text.toString()
            val iprice = holder.itemPriceTextView.text.toString()
            val icount = holder.itemcountTextView.text.toString()

            itemClickListener.callActivity(iname,iprice,icount.toInt())

        }
    }


    override fun getItemCount(): Int {
     return itemName.size
    }


}