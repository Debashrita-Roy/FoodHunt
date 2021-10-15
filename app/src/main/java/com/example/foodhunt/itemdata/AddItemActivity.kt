package com.example.foodhunt.itemdata

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.foodhunt.hoteldata.AllHotelActivity
import com.example.foodhunt.hoteldata.Item
import com.example.foodhunt.R
import com.example.foodhunt.database.auth
import com.example.foodhunt.database.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_item.*
import java.util.*

class AddItemActivity : AppCompatActivity() {

    lateinit var itemNameEditText: EditText
    lateinit var itemPriceEditText: EditText
    lateinit var addItemButton: Button
    var itemList = mutableListOf<Item>()

    lateinit var itemImage : ImageView
    lateinit var uploadItemButton: Button

    lateinit var database: DatabaseReference
    private var storageReference: StorageReference? = null
    lateinit var imageUri: Uri
    var image: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        supportActionBar?.hide()

        itemNameEditText = findViewById(R.id.itemNameE)
        itemPriceEditText = findViewById(R.id.itemPriceE)
        addItemButton = findViewById(R.id.addItemB)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        storageReference = FirebaseStorage.getInstance().reference

        itemImage = findViewById(R.id.itemIV)
        uploadItemButton = findViewById(R.id.uploadItemB)

        itemImage.setOnClickListener {
            startFileChooser()
        }

        uploadItemButton.setOnClickListener {
            uploadImage()
        }

        addItemB.setOnClickListener {
            val i = intent.extras
            val hotName = i?.getString("Hotelname")

            Log.d("AddItemActivity", "Hotel name : $hotName")
            val iname = itemNameEditText.text.toString()
            val iprice = itemPriceEditText.text.toString()

            val item = Item(iname,iprice.toInt(),0,image.toString())

            val itemRef = db.getReference("Hotels/Hotel Name/$hotName/Items")
            itemRef.child(item.itemName.toString()).setValue(item)
            Toast.makeText(this, "$iname added successfully in Hotel $hotName", Toast.LENGTH_SHORT)
                .show()
            itemNameEditText.setText("")
            itemPriceEditText.setText("")

        }

    //    itemList = arrayListOf<Item>()

    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri).addOnSuccessListener {

            it.storage.downloadUrl.addOnSuccessListener {
                image = it
            }

            itemImage.setImageURI(null)
            Toast.makeText(this, "Successfully Uploaded", Toast.LENGTH_LONG).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this, "Failed to upload", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {

            imageUri = data?.data!!
            itemImage.setImageURI(imageUri)

        }
    }

    private fun startFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }

    fun showHotel(view: View) {
        val i = Intent(this, AllHotelActivity::class.java)
        startActivity(i)
    }
}