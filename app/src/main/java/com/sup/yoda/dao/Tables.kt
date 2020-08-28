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

  const val SQL_CREATE_TABLE_USER =
    "CREATE TABLE ${UserContract.UserTable.TABLE_NAME} (" +
            "${UserContract.UserTable.COLUMN_NAME_ID} TEXT," +
            "${UserContract.UserTable.COLUMN_NAME_ID_ZENDESK} TEXT," +
            "${UserContract.UserTable.COLUMN_NAME_NAME} TEXT," +
            "${UserContract.UserTable.COLUMN_NAME_EMAIL} TEXT)"

  const val SQL_DELETE_TABLE_USER = "DROP TABLE IF EXISTS ${UserContract.UserTable.TABLE_NAME}"