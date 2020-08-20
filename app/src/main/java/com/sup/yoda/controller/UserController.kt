package com.sup.yoda.controller
import com.sup.yoda.model.User

class UserController() {

    lateinit var user: User


    fun save(id:Int,nome:String, email:String){
       lateinit var userTemp: User

        userTemp.id = id
        userTemp.nome = nome
        userTemp.email = email

        user.save(userTemp)
    }

    fun getUser(){

    }

}