package com.example.foodhunt.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//
class DBHelper(context: Context) : SQLiteOpenHelper(context,"Users.db",null,1)  {
    companion object{
        val TABLE_NAME="users_table"
        val CLM_USER_ID="user_id"
        val CLM_USER_EMAIL="user_email"
        val CLM_USER_PWD="user_password"
        val CLM_USER_ADDR="user_address"

    }
    val QUERY_CREATE_TABLE="create table $TABLE_NAME($CLM_USER_ID text,"+
            "$CLM_USER_EMAIL text, $CLM_USER_PWD text, $CLM_USER_ADDR text)"


    override fun onCreate(db: SQLiteDatabase?) {
      //executed first time when db is created
        db?.execSQL(QUERY_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldV: Int, newV: Int) {
        //upgrade db
    }
}