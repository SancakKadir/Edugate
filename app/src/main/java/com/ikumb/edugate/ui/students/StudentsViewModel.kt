package com.ikumb.edugate.ui.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ikumb.edugate.core.BaseViewModel
import com.ikumb.edugate.db.User
import javax.inject.Inject

class StudentsViewModel @Inject internal constructor() : BaseViewModel() {

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    fun getStudents() {
        FirebaseDatabase.getInstance().reference.child("Users")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val data = p0.children
                    val innerUsers = arrayListOf<User>()
                    data.forEach {
                        val user = it.getValue(User::class.java)
                        if (user != null && user.isStudent == true) {
                            innerUsers.add(user)
                        }
                    }
                    _usersLiveData.postValue(innerUsers)
                }
            })
    }
}