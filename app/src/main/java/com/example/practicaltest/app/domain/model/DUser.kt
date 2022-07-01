package com.example.practicaltest.app.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practicaltest.core.util.DataBase

@Entity(tableName = DataBase.DB_TBL_NAME)
data class DUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = DataBase.DB_TBL_USER_ID)
    var userId: String? = null,

    @ColumnInfo(name = DataBase.DB_TBL_FIRST_NAME)
    var firstName: String? = null,

    @ColumnInfo(name = DataBase.DB_TBL_LAST_NAME)
    var lastName: String? = null,

    @ColumnInfo(name = DataBase.DB_TBL_EMAIL)
    var email: String? = null,

    @ColumnInfo(name = DataBase.DB_TBL_PASSWORD)
    var password: String? = null
)