package com.sup.yoda.model

import android.content.Context
import com.sup.yoda.dao.FeedbackDAO

open class Feedback(id: Int, idUserFor:String, nameUserFor:String, idUserFrom:String,
                    nameUserFrom:String, message:String, type:String, isAnonymous:Int, date:String ){

    internal var id:Int
    internal var idUserFor:String
    internal var nameUserFor:String
    internal var idUserFrom:String
    internal var nameUserFrom:String
    internal var message:String
    internal var type:String
    internal var isAnonymous:Int
    internal var date:String

    init {
        this.id = id
        this.idUserFor = idUserFor
        this.nameUserFor = nameUserFor
        this.idUserFrom = idUserFrom
        this.nameUserFrom = nameUserFrom
        this.message = message
        this.type = type
        this.isAnonymous = isAnonymous
        this.date = date
    }

    fun save(id: Int, idUserFor:Int, nameUserFor:String, idUserFrom:String,
             nameUserFrom:String, message:String, type:String, isAnonymous:Int , date:String, context: Context){
        val feedbackDao: FeedbackDAO = FeedbackDAO(context)
        feedbackDao.insertFeedback(id, idUserFor, nameUserFor, idUserFrom,
            nameUserFrom, message, type, isAnonymous, date)

    }

}