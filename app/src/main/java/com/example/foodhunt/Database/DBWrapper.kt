package com.example.foodhunt.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.foodhunt.Order
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

    fun addOrder(order : Order): Long {
        val rowData=ContentValues()
        rowData.put(DBHelper2.CLM_ITEM_NAME,order.itemName)
        rowData.put(DBHelper2.CLM_ITEM_PRICE,order.itemPrice)
        rowData.put(DBHelper2.CLM_ITEM_COUNT,order.itemCount)

        return db.insert(DBHelper2.TABLE_ORDER_NAME, null, rowData)
    }

    fun getUser(): Cursor {
        //select query
        val clms= arrayOf(DBHelper.CLM_USER_ID, DBHelper.CLM_USER_EMAIL, DBHelper.CLM_USER_PWD, DBHelper.CLM_USER_ADDR)
        return db.query(
            DBHelper.TABLE_NAME,clms,null,
            null,null,null,null)
    }


    fun getOrder(): Cursor {
        //select query
        val clms= arrayOf(DBHelper2.CLM_ITEM_NAME, DBHelper2.CLM_ITEM_PRICE, DBHelper2.CLM_ITEM_COUNT)
        return db.query(
            DBHelper2.TABLE_ORDER_NAME,clms,null,
            null,null,null,null)
    }

    fun editOrder(order: Order): Int{
        val rowData = ContentValues()
        rowData.put(DBHelper2.CLM_ITEM_NAME,order.itemName)
        rowData.put(DBHelper2.CLM_ITEM_PRICE,order.itemPrice)
        rowData.put(DBHelper2.CLM_ITEM_COUNT,order.itemCount)
        val args = arrayOf("${order.itemName}")

        return db.update(DBHelper.TABLE_ORDER_NAME, rowData, "${DBHelper2.CLM_ITEM_NAME} = ?", args)
    }

}