package com.example.foodhunt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private var context: AllHotelActivity,
                   private var hotelName: ArrayList<String>,
                   private var items: ArrayList<String>) : RecyclerView.Adapter<HotelAdapter.HotelHolder>() {

 inner class HotelHolder(v: View): RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.hotelNameT)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.HotelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_hotel_list_item,parent,false)
        return HotelHolder(view)
    }

    override fun onBindViewHolder(holder: HotelAdapter.HotelHolder, position: Int) {
        holder.name.text = hotelName[position]

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click
            Toast.makeText(context,hotelName[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return hotelName.size
    }

}