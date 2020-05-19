package com.ikumb.edugate.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ikumb.edugate.core.BaseViewModel
import com.ikumb.edugate.db.MatBilLessons
import javax.inject.Inject

class DashboardFragmentViewModel @Inject internal constructor() : BaseViewModel() {

    private val _usersLiveData: MutableLiveData<MatBilLessons> = MutableLiveData()
    val usersLiveData: LiveData<MatBilLessons> = _usersLiveData
    private val _usersLiveDataFinal: MutableLiveData<MatBilLessons> = MutableLiveData()
    val usersLiveDataFinal: LiveData<MatBilLessons> = _usersLiveDataFinal


    fun getStudentsVisa() {
        FirebaseDatabase.getInstance().reference.child("Lessons").child(mAuth.uid.toString())
            .child("visa")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val lesson = p0.getValue(MatBilLessons::class.java)
                    _usersLiveData.postValue(lesson)
                }
            })
    }

    fun getStudentsFinal() {
        FirebaseDatabase.getInstance().reference.child("Lessons").child(mAuth.uid.toString())
            .child("final")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val lesson = p0.getValue(MatBilLessons::class.java)
                    _usersLiveDataFinal.postValue(lesson)
                }
            })
    }


}
