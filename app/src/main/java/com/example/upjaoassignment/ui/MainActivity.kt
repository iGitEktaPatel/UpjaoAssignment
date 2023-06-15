package com.example.upjaoassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upjaoassignment.R
import com.example.upjaoassignment.adapter.ListDataAdapter
import com.example.upjaoassignment.model.User
import com.example.upjaoassignment.repository.AppRepository
import com.example.upjaoassignment.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private lateinit var binding : ActivityMainBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var adapter: ListDataAdapter
    private lateinit var appRepository: AppRepository
    private val userListObserver = Observer<List<User?>?> {
        //ui update
        initUI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        init()
        initObserver()
        initCLickListner()
    }

    private fun init() {
        appRepository = AppRepository(this.application)
        appViewModel = ViewModelProvider(this)[AppViewModel::class.java]

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.setHasFixedSize(true)
        adapter = ListDataAdapter(appViewModel.userList.value)

    }

    private fun initObserver() {
        appViewModel.userList.observe(this, userListObserver)
        appRepository.userDao?.getAll()?.observe(this) {
            //onchange
            appViewModel.userList.postValue(it)
        }
    }

    private fun initUI(userList: List<User?>?) {
        rvList.adapter = adapter
        adapter.setListData(userList)
        if (userList?.isNullOrEmpty() == true) {
            txtNoData.visibility = View.VISIBLE
        } else
            txtNoData.visibility = View.GONE
    }

    private fun initCLickListner() {
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }
}