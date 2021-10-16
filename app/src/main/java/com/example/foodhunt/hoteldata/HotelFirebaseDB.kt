package com.example.foodhunt.hoteldata

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.foodhunt.itemdata.AddItemActivity
import com.example.foodhunt.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_hotel_firebase_db.*
import java.util.*


class HotelFirebaseDB : AppCompatActivity(), View.OnClickListener {

    lateinit var db: FirebaseDatabase
    lateinit var nameEditText: EditText
    lateinit var descEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var latEditText: EditText
    lateinit var longEditText: EditText

    lateinit var hotelImage: ImageView
    lateinit var uploadButton: Button

    lateinit var database: DatabaseReference
    private var storageReference: StorageReference? = null
    lateinit var imageUri: Uri
    var image: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_firebase_db)
        supportActionBar?.hide()

        nameEditText = findViewById(R.id.hotNameE)
        descEditText = findViewById(R.id.descE)
        addressEditText = findViewById(R.id.addrE)
        latEditText = findViewById(R.id.latE)
        longEditText = findViewById(R.id.longE)

        hotelImage = findViewById(R.id.hotelIV)
        uploadButton = findViewById(R.id.uploadB)

//        addCreateFirebase()
        db = FirebaseDatabase.getInstance()

        storageReference = FirebaseStorage.getInstance().reference

        hotelImage.setOnClickListener {
            startFileChooser()
        }

        uploadButton.setOnClickListener {
            uploadImage()
        }

        addHotelB.setOnClickListener {
            val name = nameEditText.text.toString()
            val desc = descEditText.text.toString()
            val addr = addressEditText.text.toString()
            val lat = latEditText.text.toString()
            val long = longEditText.text.toString()

            if (name.isNotEmpty() && desc.isNotEmpty() && addr.isNotEmpty() && lat.isNotEmpty() && long.isNotEmpty()) {
                val hotOwner =
                    Hotel(101, name, desc, addr, lat.toDouble(), long.toDouble(), image.toString())
                val hotRef = db.getReference("Hotels/Hotel Name")
                hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)

                val b = Bundle()
                b.putString("Hotelname", hotOwner.hotelName)

                val i = Intent(this, AddItemActivity::class.java)
                i.putExtras(b)
                startActivity(i)
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
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

            hotelImage.setImageURI(null)
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
            hotelImage.setImageURI(imageUri)

        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}



