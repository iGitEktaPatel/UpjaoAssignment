package com.example.upjaoassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.upjaoassignment.model.User
import com.example.upjaoassignment.repository.AppRepository

class AppViewModel(application: Application) : AndroidViewModel(application) {

    val userList: MutableLiveData<List<User?>?> = MutableLiveData()

    private val appRepository: AppRepository = AppRepository(application)

    init {
        val dbList = appRepository.getAllUser?.value
        if (!dbList.isNullOrEmpty()) {
            userList.postValue(dbList)
        } else {
            userList.postValue(ArrayList())
        }
    }
}