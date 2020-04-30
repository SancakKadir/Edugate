package com.ikumb.edugate.ui.after_register

import android.icu.util.GregorianCalendar
import androidx.databinding.ObservableField
import com.google.firebase.database.FirebaseDatabase
import com.ikumb.edugate.core.BaseViewModel
import com.ikumb.edugate.db.Lesson
import com.ikumb.edugate.db.User
import com.ikumb.edugate.utils.domain.logE
import com.ikumb.edugate.utils.domain.logV
import javax.inject.Inject

class AfterRegisterViewModel @Inject internal constructor() : BaseViewModel() {

    var name: ObservableField<String> = ObservableField("")
    var surname: ObservableField<String> = ObservableField("")
    var department: ObservableField<String> = ObservableField("")
    var userBirthDay: ObservableField<String> = ObservableField("")
    var saveSuccess: ObservableField<Boolean> = ObservableField(false)


    fun getValidationMessages(): Boolean {
        var result = true
        var message = ""
        if (name.get().isNullOrEmpty()) {
            result = false
            message = "Lütfen isim alanını boş bırakmayınız."
        } else if (surname.get().isNullOrEmpty()) {
            result = false
            message = "Lütfen soyadı alanını boş bırakmayınız."
        } else if (department.get().isNullOrEmpty()) {
            result = false
            message = "Lütfen bölümünüzü girin."
        }

        if (message.isNotEmpty()) {
            toastLiveData.postValue(message)
        }

        return result
    }

    fun AddUserToFirebase() {
        val ref = FirebaseDatabase.getInstance().reference.child("Users")
        val user = User(
            name.get().toString(),
            surname.get().toString(),
            userBirthDay.get().toString(),
            department.get().toString()
        )
        ref.child(mAuth.currentUser?.uid.toString()).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                logV("user save succes")
            } else {
                logE(task.exception?.printStackTrace().toString())
            }
        }
    }



}