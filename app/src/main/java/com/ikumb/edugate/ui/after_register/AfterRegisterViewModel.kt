package com.ikumb.edugate.ui.after_register

import androidx.databinding.ObservableField
import com.google.firebase.database.FirebaseDatabase
import com.ikumb.edugate.core.BaseViewModel
import com.ikumb.edugate.db.MatBilLessons
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
        val depart = department.get().toString()
        var result = true
        var message = ""
        if (name.get().isNullOrEmpty()) {
            result = false
            message = "Lütfen isim alanını boş bırakmayınız."
        } else if (surname.get().isNullOrEmpty()) {
            result = false
            message = "Lütfen soyadı alanını boş bırakmayınız."
        } else if (depart.isNullOrEmpty()) {
            result = false
            message = "Lütfen bölümünüzü girin."
        } else if (depart != "matematik bilgisayar") {
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
            department.get().toString(),
            true
        )
        ref.child(mAuth.currentUser?.uid.toString()).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                logV("user save succes")

            } else {
                logE(task.exception?.printStackTrace().toString())
            }
        }
    }

    fun AddLessonsToFirebase() {
        if (department.get().toString() == "matematik bilgisayar") {
            val ref = FirebaseDatabase.getInstance().reference.child("Lessons")
            val lessons = MatBilLessons(
                "girilmedi", "girilmedi", "girilmedi", "girilmedi",
                "girilmedi", "girilmedi", "girilmedi", "girilmedi",
                "girilmedi", "girilmedi"
            )
            ref.child(mAuth.currentUser?.uid.toString()).child("visa").setValue(lessons)
            ref.child(mAuth.currentUser?.uid.toString()).child("final").setValue(lessons)
        }
    }
}