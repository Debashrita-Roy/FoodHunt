package com.example.foodhunt

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_hotel_firebase_db.*
import java.io.IOException
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


//    fun addHotel(view: View) {  // adding hotels dynamically  // onClick for add hotel button
//
//        val name = nameEditText.text.toString()
//        val desc = descEditText.text.toString()
//        val addr = addressEditText.text.toString()
//        val lat = latEditText.text.toString()
//        val long = longEditText.text.toString()
//
//        val pd = ProgressDialog(this)
//        pd.setTitle("Uploading")
//        pd.show()
//        val formatter = java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
//        val now = Date()
//        val fileName = formatter.format(now)
//
//        if (name.isNotEmpty() && desc.isNotEmpty()) {
//            val imageRef = FirebaseStorage.getInstance().reference.child("Hotels/Hotel Name/$name")
//            imageRef.putFile(imageUri!!)
//                .addOnSuccessListener {
//                    it.storage.downloadUrl.addOnSuccessListener {
//                        image = it
//                        val imageid = image.toString()
//
//                        db = FirebaseDatabase.getInstance()
//                        val hotRef = db.getReference("Hotels/Hotel Name")
//
//                        val hotOwner = Hotel(101, name, desc, addr, lat.toDouble(), long.toDouble())
//
//                        hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)
//
//                        val b = Bundle()
//                        b.putString("Hotelname", hotOwner.hotelName)
//
//                        val i = Intent(this, AddItemActivity::class.java)
//                        i.putExtras(b)
//                        startActivity(i)
//
//
//                        val img = Hotel(0, null, null, null, 0.0, 0.0, imageid)
//                        hotRef.child(fileName).setValue(img)
//
//
//                    }
//
//                    pd.dismiss()
//                    Toast.makeText(applicationContext, "File  Uploaded", Toast.LENGTH_LONG).show()
//
//                }.addOnFailureListener { p0 ->
//                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
//
//                }.addOnProgressListener { p0 ->
//                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
//                    pd.setMessage("Uploaded ${progress.toInt()}%")
//                }
//        }
//
////            val hotOwner = Hotel(101, name, desc, addr, lat.toDouble(), long.toDouble())
////            val hotRef = db.getReference("Hotels/Hotel Name")
////            hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)
////
////            val b = Bundle()
////            b.putString("Hotelname",hotOwner.hotelName)
////
////            val i = Intent(this,AddItemActivity::class.java)
////            i.putExtras(b)
////            startActivity(i)
////        }
////        else{
////            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
////        }
//
////        ===============  Image part ================================================
//
//
////        val imageRef = FirebaseStorage.getInstance().reference.child("Hotels/Hotel Name")
////        imageRef.putFile(imageUri!!)
////            .addOnSuccessListener {
////                it.storage.downloadUrl.addOnSuccessListener {
////                    image=it
////                    db = FirebaseDatabase.getInstance()
////                    val stdref = db.getReference("Image")
////                    val imageid = image.toString()
////                    val img = Hotel(0,null,null,null,0.0,0.0,imageid)
////                    stdref.child(fileName).setValue(img)
////
////
////                }
////
////                pd.dismiss()
////                Toast.makeText(applicationContext,"File  Uploaded", Toast.LENGTH_LONG).show()
////
////            }.addOnFailureListener{p0 ->
////                Toast.makeText(applicationContext,p0.message, Toast.LENGTH_LONG).show()
////
////            }.addOnProgressListener { p0->
////                var progress = (100.0 * p0.bytesTransferred)/ p0.totalByteCount
////                pd.setMessage("Uploaded ${progress.toInt()}%")
////            }
//
////    ============================================================================================
////        }
//
//    }
//
//override fun onClick(p0: View?) {
//    TODO("Not yet implemented")
//}
//}


