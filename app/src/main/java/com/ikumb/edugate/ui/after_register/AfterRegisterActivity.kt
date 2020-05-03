package com.ikumb.edugate.ui.after_register

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseActivity
import com.ikumb.edugate.databinding.ActivityAfterRegisterBinding
import com.ikumb.edugate.ui.main.MainActivity
import java.util.*

class AfterRegisterActivity :
    BaseActivity<AfterRegisterViewModel, ActivityAfterRegisterBinding>(AfterRegisterViewModel::class.java) {

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    override fun getLayoutRes() = R.layout.activity_after_register

    override fun initViewModel(viewModel: AfterRegisterViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.editTextBirthday.showSoftInputOnFocus = false
        binding.editTextBirthday.keyListener = null
        binding.editTextBirthday.setOnClickListener {
            openDatePickerDialog()
        }
        binding.tilBirthday.setOnClickListener {
            openDatePickerDialog()
        }
        binding.buttonDone.setOnClickListener {
            showProgress()
            if (viewModel.getValidationMessages()) {
                runOnUiThread {
                    viewModel.saveSuccess.set(true)
                    hideProgress()
                    viewModel.AddUserToFirebase()

                    FirebaseDatabase.getInstance().getReference("Examdates")
                        .child(viewModel.department.get().toString())
                        .addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                if (p0.exists()) {
                                    Log.d("logcuk", "kardesim sınavlar var zaten ne istiyon")
                                } else {
                                    if (viewModel.department.get()
                                            .toString() == "matematik bilgisayar"
                                    ) {
                                        viewModel.AddExamDates("Analiz 1")
                                        viewModel.AddExamDates("Analitik Geometri")
                                        viewModel.AddExamDates("Soyut Matematik")
                                        viewModel.AddExamDates("Bilgisayar Bilimlerine Giriş")
                                    }
                                    if (viewModel.department.get().toString() == "mimarlık") {
                                        viewModel.AddExamDates("Mimari Tasarım")
                                        viewModel.AddExamDates("Temel Tasarım")
                                        viewModel.AddExamDates("Mimarlık için Matematik")
                                        viewModel.AddExamDates("Academic English for Architecture")
                                    }
                                }
                            }

                        })

                    if (viewModel.department.get().toString()=="matematik bilgisayar"){
                        viewModel.AddLessonToFirebase("Analiz 1")
                        viewModel.AddLessonToFirebase("Analitik Geometri")
                        viewModel.AddLessonToFirebase("Soyut Matematik")
                        viewModel.AddLessonToFirebase("Bilgisayar Bilimlerine Giriş")
                    }
                    if(viewModel.department.get().toString()=="mimarlık"){
                        viewModel.AddLessonToFirebase("Mimari Tasarım")
                        viewModel.AddLessonToFirebase("Temel Tasarım")
                        viewModel.AddLessonToFirebase("Mimarlık için Matematik")
                        viewModel.AddLessonToFirebase("Academic English for Architecture")
                    }
                    val intent = Intent(this@AfterRegisterActivity, MainActivity::class.java)
                    runOnUiThread {
                        startActivity(intent)
                        finish()
                    }
                }

            }

        }

    }


    private fun openDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                viewModel.userBirthDay.set("$day/${month + 1}/$year")
            },
            year, month, day
        )
        datePicker.datePicker.maxDate = c.timeInMillis
        datePicker.show()
    }


}
