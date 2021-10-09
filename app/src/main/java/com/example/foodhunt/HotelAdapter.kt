package com.example.foodhunt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(val data: MutableList<String>) : RecyclerView.Adapter<HotelAdapter.HotelHolder>() {

    class HotelHolder(v: View): RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.hotelNameT)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.HotelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_hotel_list_item,parent,false)
        return HotelHolder(view)
    }

    override fun onBindViewHolder(holder: HotelAdapter.HotelHolder, position: Int) {
        val hot = data[position]
        holder.name.text = hot
    }

    override fun getItemCount(): Int {
        return data.size
    }
}