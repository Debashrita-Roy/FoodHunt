package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.foodhunt.Database.DBHelper
import com.example.foodhunt.Database.DBWrapper

val userList= mutableListOf<Users>()
class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var registerButton: Button
    lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        registerButton = findViewById(R.id.registerB)

        registerButton.setOnClickListener(this)
        registerForContextMenu(registerButton)

        signInButton = findViewById(R.id.signinB)
        signInButton.setOnClickListener(this)

    }
    val MENU_REGISTER_ADMIN = 1
    val MENU_REGISTER_USER = 2

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when(v?.id){
            R.id.registerB->{
                menu?.setHeaderTitle("Register as") // set header
                menu?.add(ContextMenu.NONE,MENU_REGISTER_ADMIN,0,"as Admin")
                menu?.add(0,MENU_REGISTER_USER,0,"as User")
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            MENU_REGISTER_USER ->{
                val i= Intent(this,RegisterActivity::class.java)
                startActivity(i)
            }
            MENU_REGISTER_ADMIN->{
                val i = Intent(this,HotelFirebaseDB::class.java)
                startActivity(i)
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.signinB->{

                val i= Intent(this, SignInActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun populateList() {
        val wrapper= DBWrapper(this)
        val resultC=wrapper.getUser()
        userList.clear()

        if(resultC.count>0){
            resultC.moveToFirst()
            val idx_id=resultC.getColumnIndex(DBHelper.CLM_USER_ID)
            val idx_email=resultC.getColumnIndex(DBHelper.CLM_USER_EMAIL)
            val idx_password=resultC.getColumnIndex(DBHelper.CLM_USER_PWD)

            do{
                val id=resultC.getString(idx_id)
                val email=resultC.getString(idx_email)
                val password=resultC.getString(idx_password)

                val use=Users(id,email,password)
                userList.add(use)
            }
            while (resultC.moveToNext())
            //Toast.makeText(this,"No of users: ${userList.size}",Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "No Users Added yet", Toast.LENGTH_SHORT).show()
    }
}

