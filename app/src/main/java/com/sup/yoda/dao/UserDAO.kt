package com.sup.yoda.dao

import android.content.ContentValues
import android.content.Context
import com.sup.yoda.model.User

class UserDAO(context:Context){

    private val dbHelper = DataBase(context)
    private val db = dbHelper.writableDatabase


    fun insertUser(id: Int,idZendesk:String, name:String, email: String, isLogged:Int){

        val values = ContentValues().apply {
            put(UserContract.UserTable.COLUMN_NAME_ID, id.toString())
            put(UserContract.UserTable.COLUMN_NAME_ID_ZENDESK, idZendesk)
            put(UserContract.UserTable.COLUMN_NAME_NAME, name)
            put(UserContract.UserTable.COLUMN_NAME_EMAIL, email)
            put(UserContract.UserTable.COLUMN_IS_LOGGED, isLogged)
        }

        if (!userExist(id)){
            val newRowId = db.insert(UserContract.UserTable.TABLE_NAME, null, values)
        }



    }

    fun getListUsers(): ArrayList<User>{

        val users = ArrayList<User>()

        val selectQuery = "SELECT * FROM ${UserContract.UserTable.TABLE_NAME}"

        users.clear()
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
             while (cursor.moveToNext()) {
                var userNew:User = User(0,"","","",0)
                userNew.id =(cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID))).toInt()
                userNew.idZendesk = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID_ZENDESK))
                userNew.nome = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_NAME))
                userNew.email = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_EMAIL))
                userNew.isLogged = cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_IS_LOGGED)).toInt()
                users.add(userNew)
            }
        }
        cursor.close()

        return users

    }

    fun getUserLogged(): User{
        var userNew: User = User(0,"","","",0)

        val selectQuery = "SELECT * FROM ${UserContract.UserTable.TABLE_NAME} WHERE is_logged = 1"

        val cursor = db.rawQuery(selectQuery, null)


            if(cursor.moveToFirst()) {

                userNew.id =
                    (cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID))).toInt()
                userNew.idZendesk =
                    cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID_ZENDESK))
                userNew.nome =
                    cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_NAME))
                userNew.email =
                    cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_EMAIL))
                userNew.isLogged =
                    (cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_IS_LOGGED))).toInt()
            }

        cursor.close()

        return userNew
    }



    fun userExist(id: Int): Boolean{
        var userNew: User = User(0,"","","",0)

        val selectQuery = "SELECT * FROM ${UserContract.UserTable.TABLE_NAME} WHERE id = $id"

        val cursor = db.rawQuery(selectQuery, null)

        if(cursor.moveToFirst()) {

            userNew.id = (cursor.getString(cursor.getColumnIndex(UserContract.UserTable.COLUMN_NAME_ID))).toInt()

            if(userNew.id == id){
                return true
            }


        }

        cursor.close()

            return false

    }


}