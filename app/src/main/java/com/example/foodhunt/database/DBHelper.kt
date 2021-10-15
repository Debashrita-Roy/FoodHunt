package com.example.foodhunt.database

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

        val TABLE_ORDER_NAME = "order_table"
        val CLM_ITEM_NAME = "item_name"
        val CLM_ITEM_PRICE = "item_price"
        val CLM_ITEM_COUNT = "count"


    }
    val QUERY_CREATE_TABLE="create table $TABLE_NAME($CLM_USER_ID text,"+
            "$CLM_USER_EMAIL text, $CLM_USER_PWD text, $CLM_USER_ADDR text)"

    val QUERY_CREATE_TABLE2="create table $TABLE_ORDER_NAME($CLM_ITEM_NAME text,"+
            "$CLM_ITEM_PRICE text, $CLM_ITEM_COUNT text)"


    override fun onCreate(db: SQLiteDatabase?) {
      //executed first time when db is created
        db?.execSQL(QUERY_CREATE_TABLE)
        db?.execSQL(QUERY_CREATE_TABLE2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldV: Int, newV: Int) {
        //upgrade db
    }
}

class DBHelper2(context: Context) : SQLiteOpenHelper(context,"Orders.db",null,1) {
    companion object {
        val TABLE_ORDER_NAME = "order_table"
        val CLM_ITEM_NAME = "item_name"
        val CLM_ITEM_PRICE = "item_price"
        val CLM_ITEM_COUNT = "count"
    }

        val QUERY_CREATE_TABLE2 =
            "create table ${DBHelper2.TABLE_ORDER_NAME}(${DBHelper2.CLM_ITEM_NAME} text," +
                    "${DBHelper2.CLM_ITEM_PRICE} text, ${DBHelper2.CLM_ITEM_COUNT} text)"

        override fun onCreate(db: SQLiteDatabase?) {
            //executed first time when db is created
            db?.execSQL(QUERY_CREATE_TABLE2)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, oldV: Int, newV: Int) {
            //upgrade db
        }
    }
