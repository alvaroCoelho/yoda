package com.sup.yoda.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.sup.yoda.model.User

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

    fun getListUsers(): ArrayList<User>{

        val users = ArrayList<User>()

        val selectQuery = "SELECT  * FROM $UserContract.UserTable.TABLE_NAME"


        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                var userNew:User = User(0,"","","")
                userNew.id =(cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID))).toInt()
                userNew.idZendesk = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID_ZENDESK))
                userNew.nome = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_NAME))
                userNew.email = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_EMAIL))
                users.add(userNew)
            }
        }
        cursor.close()

        return users

    }

}