package com.example.foodhunt

import android.app.Activity
import android.content.Intent
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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_hotel_firebase_db.*
import java.io.IOException
import java.util.*


class HotelFirebaseDB : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: DisplayItemActivity
    lateinit var db: FirebaseDatabase
    lateinit var nameEditText: EditText
    lateinit var descEditText: EditText
    lateinit var addHotelButton: Button
    lateinit var addressEditText: EditText
    lateinit var latEditText: EditText
    lateinit var longEditText: EditText
    lateinit var imageNameEditText :EditText
    lateinit var chooseButton: Button
    lateinit var uploadButton: Button
   // lateinit var  imageUri : Uri
    private val PICK_IMAGE_REQUEST = 1
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_firebase_db)
        supportActionBar?.hide()

        nameEditText = findViewById(R.id.hotNameE)
        descEditText = findViewById(R.id.descE)
        addressEditText = findViewById(R.id.addrE)
        latEditText = findViewById(R.id.latE)
        longEditText = findViewById(R.id.longE)

        addHotelButton = findViewById(R.id.addHotelB)
        chooseButton = findViewById(R.id.chooseImageb)
        uploadButton = findViewById(R.id.uploadB)
        db = FirebaseDatabase.getInstance()

        //addCreateFirebase()  // predefined hotels have been added

        chooseButton.setOnClickListener(View.OnClickListener {
            openFileChooser()
        })



    }
    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageV.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun addHotel(view: View) {  // adding hotels dynamically  // onClick for add hotel button

        val name = nameEditText.text.toString()
        val desc = descEditText.text.toString()
        val addr = addressEditText.text.toString()
        val lat = latEditText.text.toString()
        val long = longEditText.text.toString()

//        ===============  Image part ================================================
        if (filePath != null) {
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask =
                uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    return@Continuation ref.downloadUrl
                })?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        val hotOwner =
                            Hotel(
                                101,
                                name,
                                desc,
                                addr,
                                lat.toDouble(),
                                long.toDouble(),
                                downloadUri!!
                            )
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
        } else {
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }
//    ============================================================================================
//        if (name.isNotEmpty() && desc.isNotEmpty()) {
//            val hotOwner = Hotel(101, name, desc, addr, lat.toDouble(), long.toDouble(),imageUri)
//            val hotRef = db.getReference("Hotels/Hotel Name")
//            hotRef.child(hotOwner.hotelName.toString()).setValue(hotOwner)
//
//            val b = Bundle()
//            b.putString("Hotelname",hotOwner.hotelName)
//
//            val i = Intent(this,AddItemActivity::class.java)
//            i.putExtras(b)
//            startActivity(i)
//        }
//        else{
//            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onClick(v: View?) {
    }

    fun chooseImage(view: View) {

    }

}


