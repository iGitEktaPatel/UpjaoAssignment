package com.example.upjaoassignment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.upjaoassignment.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): LiveData<List<User?>?>?

    @Query("SELECT * FROM User where id= :id")
    fun getUser(id: Int): LiveData<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: User): Long

    @Delete
    fun delete(user: User?)
}