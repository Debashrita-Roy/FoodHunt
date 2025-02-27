package com.example.foodhunt.itemdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhunt.*
import com.example.foodhunt.database.db
import com.example.foodhunt.hoteldata.Hotel
import com.example.foodhunt.hoteldata.Item
import com.example.foodhunt.orderdetails.OrderSummaryActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_all_item.*

class AllItemActivity : AppCompatActivity(), ItemClickListener {

    lateinit var itemRView: RecyclerView
    var itemList = mutableListOf<Item>()
    var hotelList = mutableListOf<Hotel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_item)
        supportActionBar?.hide()

        itemRView = findViewById(R.id.itemRecV)
        itemRView.setHasFixedSize(true)
        itemRView.layoutManager = LinearLayoutManager(this)
        getHotelData()
    }

    override fun callActivity(itemname: String, itemprice: String, itemcount: Int) {
        val b = Bundle()
        b.putString("iname", itemname)
        b.putString("iprice", itemprice)
        b.putInt("icount", itemcount)
        val i = Intent(this, OrderSummaryActivity::class.java)
        i.putExtras(b)
        startActivity(i)
    }

    override fun addCount(item: Item, position: Int, count: Int) {
        item.count = item.count + 1
        itemRView.adapter?.notifyItemChanged(position)

    }

    override fun subtractCount(item: Item, position: Int, count: Int) {
        item.count = item.count - 1
        itemRView.adapter?.notifyItemChanged(position)
    }

    fun getHotelData() {
        db = FirebaseDatabase.getInstance()
        val hotRef = db.getReference("Hotels").child("Hotel Name")

        hotRef.addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val hot = i.getValue(Hotel::class.java)
                        hotelList.add(hot!!)
                        getItemsData(hot)
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun getItemsData(hot : Hotel) {

        val hotDescRef =
            db.getReference("Hotels").child("Hotel Name").child("${hot.hotelName}").child("Items")
        hotDescRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snap: DataSnapshot) {
                if (snap.exists()) {
                    for (i in snap.children) {
                        val items = i.getValue(Item::class.java)
                        itemList.add(items!!)
                        itemRView.adapter = ItemAdapter(itemList,this@AllItemActivity)
                    }
                    Log.d("AllHotelActivity", "$itemList")
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}