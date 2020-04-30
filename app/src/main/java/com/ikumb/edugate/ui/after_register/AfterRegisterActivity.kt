package com.ikumb.edugate.ui.after_register

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.google.firebase.storage.FirebaseStorage
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseActivity
import com.ikumb.edugate.databinding.ActivityAfterRegisterBinding
import com.ikumb.edugate.ui.main.MainActivity
import com.ikumb.edugate.utils.domain.hide
import com.ikumb.edugate.utils.domain.show
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
