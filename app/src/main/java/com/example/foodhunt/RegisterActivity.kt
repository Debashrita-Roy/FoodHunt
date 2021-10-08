package com.example.foodhunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.makeText

data class Users(var userid:String, var email: String, var password: String )

class RegisterActivity : AppCompatActivity() {
    lateinit var uidEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var pwdEditText: EditText
    lateinit var confirmpwdEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        uidEditText = findViewById(R.id.userIdER)
        emailEditText = findViewById(R.id.emailER)
        pwdEditText = findViewById(R.id.pwdER)
        confirmpwdEditText = findViewById(R.id.confirmpwdER)

    }

    fun registerClick(view: View) {

        val userid = uidEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = pwdEditText.text.toString()
        val confirmpassword = confirmpwdEditText.text.toString()

        if (userid.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            /*val use = Users(userid, email, password)

            //add to database

            val wrapper = DBWrapper(this)
            val rowid = wrapper.addUser(use)
            if (rowid.toInt() != -1) {
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Error saving details..", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(this, "pls provide all info", Toast.LENGTH_SHORT).show()
*/
            when {
                userid.isEmpty() -> uidEditText.setError("Please enter user id")
                email.isEmpty() -> emailEditText.setError("Please enter email id")
                password.isEmpty() -> pwdEditText.setError("Please create a password")
                password.length < 8 -> pwdEditText.setError("password should be 8 characters long")
                confirmpassword.isEmpty() -> confirmpwdEditText.setError("Please enter user id")
                confirmpassword != password -> confirmpwdEditText.setError("Password do not match")

                else -> {
                    Toast.makeText(this, "Welcome to Login page", Toast.LENGTH_SHORT).show()
                    val use = Users(userid, email, password)

                    //add to database

                    val wrapper = DBWrapper(this)
                    val rowid = wrapper.addUser(use)
                    if (rowid.toInt() != -1) {
                        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                    } else
                        Toast.makeText(this, "Error saving details..", Toast.LENGTH_SHORT).show()

                    val i = Intent(this, SignInActivity::class.java)
                    startActivity(i)
                }
            }
        }
    }
}