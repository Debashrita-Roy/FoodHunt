package com.example.foodhunt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private var hotName: MutableList<Hotel>)
    : RecyclerView.Adapter<HotelAdapter.HotelHolder>() {

 inner class HotelHolder(v: View): RecyclerView.ViewHolder(v){
        val nameTextView = v.findViewById<TextView>(R.id.hotelNameT)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.HotelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_hotel_list_item,parent,false)
        return HotelHolder(view)
    }

    override fun onBindViewHolder(holder: HotelAdapter.HotelHolder, position: Int) {
        val hot = hotName[position]
        holder.nameTextView.text = hot.hotelName
    }

    override fun getItemCount(): Int {
        return hotName.size
    }

}