package com.example.upjaoassignment.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.upjaoassignment.database.AppDatabase
import com.example.upjaoassignment.database.UserDao
import com.example.upjaoassignment.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppRepository(application: Application) {

    var userDao: UserDao?
    private var allUserData: LiveData<List<User?>?>?
    private val database: AppDatabase? = AppDatabase.getInstance(application)

    fun insertUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val userId = userDao?.insertUser(user)
            Log.e("===TAG===","userId $userId")
        }
    }

    fun deleteUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDao?.getUser(id)?.value
            userDao?.delete(user)
        }
    }

    val getAllUser: LiveData<List<User?>?>?
        get() = allUserData

    init {
        userDao = database?.userDao()
        allUserData = userDao?.getAll()
    }
}