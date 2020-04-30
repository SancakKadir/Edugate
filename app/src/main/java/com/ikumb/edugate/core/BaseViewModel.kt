package com.ikumb.edugate.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*


open class BaseViewModel : ViewModel(){
    var progressLiveData = MutableLiveData<Boolean>()
    var toastLiveData = MutableLiveData<String>()

    lateinit var mAuth: FirebaseAuth


    lateinit var currentLocation: String

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    init {
        initFirebase()
        getUserLocation()
    }


    private fun getUserLocation() {
        currentLocation = Locale.getDefault().country.toLowerCase()

        if (currentLocation.isEmpty())
            currentLocation = "us"
    }


}