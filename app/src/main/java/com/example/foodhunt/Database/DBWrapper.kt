package com.example.foodhunt.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.foodhunt.Users

class DBWrapper (val context: Context){

    val helper : DBHelper = DBHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase


    fun addUser(use: Users): Long {
        //insert
        val rowData=ContentValues()
        rowData.put(DBHelper.CLM_USER_ID,use.userid)
        rowData.put(DBHelper.CLM_USER_EMAIL,use.email)
        rowData.put(DBHelper.CLM_USER_PWD,use.password)
        rowData.put(DBHelper.CLM_USER_ADDR, use.address)

        return db.insert(DBHelper.TABLE_NAME, null, rowData)

    }

    fun getUser(): Cursor {
        //select query
        val clms= arrayOf(DBHelper.CLM_USER_ID, DBHelper.CLM_USER_EMAIL, DBHelper.CLM_USER_PWD, DBHelper.CLM_USER_ADDR)
        return db.query(
            DBHelper.TABLE_NAME,clms,null,
            null,null,null,null)

    }
}