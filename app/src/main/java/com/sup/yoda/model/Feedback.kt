package com.sup.yoda.model

open class Feedback(id: Int, id_user_for:Int, name_user_for:String, id_user_from:Int,
                    name_user_from:String, message:String, type:String, isAnonymous:Int ){

    internal var id:Int
    internal var id_user_for:Int
    internal var name_user_for:String
    internal var id_user_from:Int
    internal var name_user_from:String
    internal var message:String
    internal var type:String
    internal var isAnonymous:Int

    init {
        this.id = id
        this.id_user_for = id_user_for
        this.name_user_for = name_user_for
        this.id_user_from = id_user_from
        this.name_user_from = name_user_from
        this.message = message
        this.type = type
        this.isAnonymous = isAnonymous
    }

}