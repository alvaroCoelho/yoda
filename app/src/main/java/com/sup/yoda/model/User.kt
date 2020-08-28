package com.sup.yoda.model

import android.content.Context
import com.sup.yoda.dao.UserDAO


open class User (id_user: Int, name_user:String, email_user: String) {

    internal var id: Int
    internal var nome: String
    internal var email: String


    init {
        this.id = id_user
        this.nome = name_user
        this.email = email_user

    }


    fun save(user:User, context: Context){
        val userDao: UserDAO = UserDAO(context)
        userDao.insertUser(user.nome, user.email)
    }

    fun delete(id:Int){

    }

}