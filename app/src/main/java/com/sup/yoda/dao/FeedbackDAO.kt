package com.sup.yoda.dao

import android.content.ContentValues
import android.content.Context

class FeedbackDAO(context: Context){

    private val dbHelper = DataBase(context)
    private val db = dbHelper.writableDatabase


    fun insertFeedback(id: Int, idUserFor:Int, nameUserFor:String, idUserFrom:String,
                       nameUserFrom:String, message:String, type:String, isAnonymous:Int){

        val values = ContentValues().apply {
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_ID, id.toString())
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_ID_USER_FOR, idUserFor)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_NAME_USER_FOR, nameUserFor)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_ID_USER_FROM, idUserFrom)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_NAME_USER_FROM, nameUserFrom)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_MESSAGE, message)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_TYPE, type)
            put(FeedbackContract.FeedbackTable.COLUMN_NAME_IS_ANONYMOUS, isAnonymous)
        }

        val newRowId = db.insert(FeedbackContract.FeedbackTable.TABLE_NAME, null, values)

    }


}