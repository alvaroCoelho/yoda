package com.sup.yoda.dao

import android.content.ContentValues
import android.content.Context

class UserDAO(context:Context){

    private val dbHelper = DataBase(context)
    private val db = dbHelper.writableDatabase


    fun insertUser(id: Int,idZendesk:String, name:String, email: String){

        val values = ContentValues().apply {
            put(UserContract.UserTable.COLUMN_NAME_ID, id.toString())
            put(UserContract.UserTable.COLUMN_NAME_ID_ZENDESK, idZendesk)
            put(UserContract.UserTable.COLUMN_NAME_NAME, name)
            put(UserContract.UserTable.COLUMN_NAME_EMAIL, email)
        }

       val newRowId = db.insert(UserContract.UserTable.TABLE_NAME, null, values)

    }

}