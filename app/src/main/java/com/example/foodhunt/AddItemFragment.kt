package com.example.foodhunt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**

fun addItem(hotel : Hotel){
val itemName = itemNameEditText.text.toString()
val itemPrice = itemPriceEditText.text.toString()
val items = Item(itemName,itemPrice.toInt(),100)
val itemRef = db.getReference("Hotels/Hotel Name/${hotel.hotelName.toString()}/Items")

}
 */
class AddItemFragment : Fragment() {

    lateinit var itemNameEditText: EditText
    lateinit var itemPriceEditText: EditText
    lateinit var addItemButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemNameEditText = view.findViewById(R.id.itemNameE)
        itemPriceEditText = view.findViewById(R.id.itemPriceE)
        addItemButton = view.findViewById(R.id.itemB)

    }

    companion object {


    }
}