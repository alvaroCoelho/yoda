package com.sup.yoda.model

import android.content.Context
import com.sup.yoda.dao.UserDAO


open class User (id_user: Int, idZendesk: String, name_user:String, email_user: String, isLogged: Int) {

    internal var id: Int
    internal var idZendesk: String
    internal var nome: String
    internal var email: String
    internal var isLogged: Int

    init {
        this.id = id_user
        this.idZendesk = idZendesk
        this.nome = name_user
        this.email = email_user
        this.isLogged = isLogged

    }

    override fun toString(): String {
        return nome
    }

    fun save(id_user: Int, idZendesk: String, name_user:String, email_user: String, isLogged: Int , context: Context){
        val userDao: UserDAO = UserDAO(context)
        userDao.insertUser(id_user,idZendesk, name_user,email_user, isLogged)
    }


    fun getList(context: Context): ArrayList<User>{
         val userDao: UserDAO = UserDAO(context)
        return  userDao.getListUsers()

    }

    fun getUserLogged(context: Context):User{
        val userDao: UserDAO = UserDAO(context)
        return  userDao.getUserLogged()
    }
}