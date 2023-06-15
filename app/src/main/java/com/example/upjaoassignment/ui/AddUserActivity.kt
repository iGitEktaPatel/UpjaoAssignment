package com.example.upjaoassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.upjaoassignment.R
import com.example.upjaoassignment.database.AppDatabase
import com.example.upjaoassignment.model.User
import com.example.upjaoassignment.repository.AppRepository
import com.facebook.stetho.Stetho

class AddUserActivity : AppCompatActivity() {
    private lateinit var appRepository: AppRepository
    private lateinit var imagePicker: ActivityResultLauncher<PickVisualMediaRequest>
    private var txtAddUserProfilePic: TextView? = null
    private var edtUserName: EditText? = null
    private var edtContactNum: EditText? = null
    private var edtLocation: EditText? = null
    private var profilePicPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        Stetho.initializeWithDefaults(application)
        appRepository = AppRepository(this.application)

        txtAddUserProfilePic = findViewById(R.id.txtAddProfilePic)
        edtUserName = findViewById(R.id.edtUserName)
        edtContactNum = findViewById(R.id.edtContactNum)
        edtLocation = findViewById(R.id.edtLocation)

        imagePicker = registerForActivityResult(PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                profilePicPath = uri.toString()
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

        initList()
        initCLickListner()
    }

    private fun initList() {

    }

    private fun initCLickListner() {
        txtAddUserProfilePic?.setOnClickListener {
            imagePicker.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        findViewById<Button>(R.id.btnAddUser).setOnClickListener {
            if(validate()){
                // add to Room
                appRepository.insertUser(
                    User(
                        userName = edtUserName?.text.toString(),
                        profilePic = profilePicPath,
                        phoneNum = edtContactNum?.text.toString(),
                        location = edtLocation?.text.toString()
                    )
                )
//                val db = Room.databaseBuilder(
//                    applicationContext,
//                    AppDatabase::class.java, "upjaoAssignmentDB"
//                ).build()
//                db.userDao().insertUser(User(
//                    userName = edtUserName?.text.toString(),
//                    profilePic = profilePicPath,
//                    phoneNum = edtContactNum?.text.toString(),
//                    location = edtLocation?.text.toString()
//                ))
            }
        }
    }

    private fun validate() : Boolean {
        if(profilePicPath.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.please_choose_profile_pic), Toast.LENGTH_SHORT).show()
            return false
        } else if(edtUserName?.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show()
            return false
        } else if(edtContactNum?.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.please_enter_phone_number), Toast.LENGTH_SHORT).show()
            return false
        } else if(edtLocation?.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.please_enter_location), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}