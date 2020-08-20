package com.sup.yoda.model

import com.sup.yoda.dao.UserDAO


open class User () {

    var id: Int = 0
    lateinit var nome: String
    lateinit var email: String
    lateinit var userDao: UserDAO


    fun save(user:User){

    }

    fun delete(id:Int){

    }

}