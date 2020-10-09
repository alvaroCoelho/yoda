package com.sup.yoda.dao

import android.provider.BaseColumns

object UserContract {
    object UserTable : BaseColumns {
        const val TABLE_NAME = "USER"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_ID_ZENDESK = "id_zendesk"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_EMAIL = "email"
    }
}

object FeedbackContract {
    object FeedbackTable : BaseColumns {
        const val TABLE_NAME = "FEEDBACK"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_ID_USER_FOR = "id_user_for"
        const val COLUMN_NAME_NAME_USER_FOR = "name_user_for"
        const val COLUMN_NAME_ID_USER_FROM = "id_user_from"
        const val COLUMN_NAME_NAME_USER_FROM = "name_user_from"
        const val COLUMN_NAME_MESSAGE = "message"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_IS_ANONYMOUS = "isAnonymous"
    }
}

  const val SQL_CREATE_TABLE_USER =
      "CREATE TABLE ${UserContract.UserTable.TABLE_NAME} (" +
              "${UserContract.UserTable.COLUMN_NAME_ID} TEXT," +
              "${UserContract.UserTable.COLUMN_NAME_ID_ZENDESK} TEXT," +
              "${UserContract.UserTable.COLUMN_NAME_NAME} TEXT," +
              "${UserContract.UserTable.COLUMN_NAME_EMAIL} TEXT)"

const val SQL_DELETE_TABLE_USER = "DROP TABLE IF EXISTS ${UserContract.UserTable.TABLE_NAME}"


const val SQL_CREATE_TABLE_FEEDBACK =
    "CREATE TABLE ${FeedbackContract.FeedbackTable.TABLE_NAME} (" +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_ID} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_ID_USER_FOR} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_NAME_USER_FOR} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_ID_USER_FROM} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_NAME_USER_FROM} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_MESSAGE} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_TYPE} TEXT," +
            "${FeedbackContract.FeedbackTable.COLUMN_NAME_IS_ANONYMOUS} INTEGER)"

const val SQL_DELETE_TABLE_FEEDBACK = "DROP TABLE IF EXISTS ${FeedbackContract.FeedbackTable.TABLE_NAME}"