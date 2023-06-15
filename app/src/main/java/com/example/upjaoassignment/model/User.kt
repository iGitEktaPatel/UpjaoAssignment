package com.example.upjaoassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "User", indices = [Index(value = ["id"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = -1,
    @ColumnInfo(name = "user_name")
    val userName: String?,
    @ColumnInfo(name = "pic_uri")
    val profilePic: String?,
    @ColumnInfo(name = "phone_num")
    val phoneNum: String?,
    @ColumnInfo(name = "location")
    val location: String?
)
