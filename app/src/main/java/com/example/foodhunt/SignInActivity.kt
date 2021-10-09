package com.example.foodhunt

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.foodhunt.R

class SignInActivity : AppCompatActivity(){

    lateinit var uidEditText: EditText
    lateinit var pwdEditText: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()

        uidEditText=findViewById(R.id.userIdE)
        pwdEditText=findViewById(R.id.pwdE)
    }

    fun signClick(view: View) {

        val userid=uidEditText.text.toString()
        val password=pwdEditText.text.toString()
        //addPredefinedHotels()
        addCreateFirebase()

        when{
            userid.isEmpty()->uidEditText.setError("Please enter user id")

            password.isEmpty()->pwdEditText.setError("Please enter password")

            password.length<8->pwdEditText.setError("Password should be 8 characters long")

            else-> Toast.makeText(this,"Confirm \nUserid: $userid \nPassword: $password", Toast.LENGTH_LONG).show()
        }

        val i = Intent(this,DisplayActivity::class.java)
        startActivity(i)
        
    }

    fun forgotPwdClick(view: View) {

        //val forgetPassword=forgetPwd.text.toString()

        Toast.makeText(this,"We have emailed you a password reset procedure, please check your email",Toast.LENGTH_LONG).show()
        displayNotification()

    }


    private fun displayNotification() {

        //get notification manager reference
        val nManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //create notifiaction objects
        var builder:Notification.Builder

        //above oreo notification channel
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("Group4Demo","Gmail",NotificationManager.IMPORTANCE_DEFAULT)

            channel.setSound(Settings.System.DEFAULT_RINGTONE_URI,null)

            nManager.createNotificationChannel(channel)

            builder=Notification.Builder(this,"Group4Demo")
        }
        else{
            builder=Notification.Builder(this)
        }
        builder.setSmallIcon(R.drawable.gmail2)
        builder.setContentTitle("Gmail")
        builder.setContentText("Password Reset. Your Verification code 242...")

        val i= Intent(this,MainActivity::class.java)
        val pi= PendingIntent.getActivity(this,0,i,0)

        builder.setContentIntent(pi)
        val myNotification=builder.build()
        myNotification.flags=Notification.FLAG_AUTO_CANCEL

        nManager.notify(1,myNotification)



    }

    fun backClick(view: View) {
       onBackPressed()
    }

    override fun onBackPressed() {
        val i= Intent(this,MainActivity::class.java)
        startActivity(i)
        super.onBackPressed()
    }


}