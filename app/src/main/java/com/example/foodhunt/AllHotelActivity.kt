package com.example.foodhunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import kotlin.collections.ArrayList

class AllHotelActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView
    lateinit var hotelAdapter: HotelAdapter

//    var hotelList = ArrayList<String>()
//    var itemList = mutableListOf<String>()

    var hotelName: ArrayList<String> = ArrayList()
    var items: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hotel)

        rView = findViewById(R.id.hotelRV)
        rView!!.setHasFixedSize(true)
        rView!!.layoutManager = LinearLayoutManager(applicationContext)


        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

//        val hotRef = db.getReference("Hotels")
//            .child("Hotel Name")
//
//        hotRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("AllHotelActivity", "\nGot Hotel : $snapshot")
//                val hotelCount = snapshot.childrenCount
//                for (i in 0..hotelCount - 1) {
//                    val hotel1 = snapshot.children.elementAt(i.toInt())
//                    hotelList.add(hotel1.child("hotelName").value.toString())
//                    Log.d("AllHotelActivity", "\nHotels are  : $hotel1\n")
//                }
//                Log.d("AllHotelActivity","hotelList : $hotelList")
//               for(j in 0..(hotelList.size - 1)){
//                   val itemnames = snapshot.children.elementAt(j).child("Items")
//                   Log.d("AllHotelActivity","items : $itemnames")
//                   itemList.add(itemnames.child("Items").value.toString())
//
//                   val hotname = hotelList[j]
//                   Log.d("AllHotelActivity","hotname : $hotname")
//
//               }
//                Log.d("AllHotelActivity","hotelList : $itemList")
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
        //       setUpRecyclerView()

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val hotelArray = obj.getJSONObject("Hotels/Hotel Name")
            for (i in 0 until hotelArray.length()) {
                val hotelDetail = hotelArray.getJSONObject(i.toString())

                hotelName.add(hotelDetail.getString("hotelName"))
                val itemDetails = hotelDetail.getJSONObject("${hotelName}/Items")
                items.add(itemDetails.getString("itemName"))
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        val hotelAdapter = HotelAdapter(this@AllHotelActivity,hotelName,items)
        rView.adapter = hotelAdapter
//        rView.layoutManager = GridLayoutManager(this,2)
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("foodHuntData.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }



//    fun setUpRecyclerView() {
//        hotelAdapter = HotelAdapter(hotelList)
//        rView.adapter = hotelAdapter
//        rView.layoutManager = GridLayoutManager(this,2)
//    }

    private fun onHotelSelected(selectedHotel : Hotel){
        Toast.makeText(this, "$selectedHotel", Toast.LENGTH_SHORT).show()
    }
}
