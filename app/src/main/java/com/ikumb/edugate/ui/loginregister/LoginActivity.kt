package com.ikumb.edugate.ui.loginregister

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.widget.Toast
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseActivity
import com.ikumb.edugate.core.Constants
import com.ikumb.edugate.databinding.ActivityLoginBinding
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.ikumb.edugate.ui.after_register.AfterRegisterActivity
import com.ikumb.edugate.ui.entry.EntryActivity
import com.ikumb.edugate.ui.main.MainActivity
import com.ikumb.edugate.utils.domain.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :
    BaseActivity<LoginViewModel, ActivityLoginBinding>(LoginViewModel::class.java) {

    override fun getLayoutRes(): Int = R.layout.activity_login


    override fun initViewModel(viewModel: LoginViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseIntent()
        initObservers()

    }

    private fun parseIntent() {
        val viewType =
            intent.getIntExtra(
                Constants.IntentName.LOGIN_ACTIVITY_TYPE,
                Constants.LoginActivityType.LOGIN_TYPE
            )
        viewModel.setType(viewType)
    }

    private fun initObservers() {
        if (viewModel.progressLiveData.hasActiveObservers())
            viewModel.progressLiveData.removeObservers(this)

        viewModel.progressLiveData.observe(
            this,
            Observer<Boolean> {
                if (it)
                    showProgress()
                else
                    hideProgress()
            }
        )


        viewModel.registerSuccess.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showRegisterSuccessDialog()
                viewModel.setType(Constants.LoginActivityType.LOGIN_TYPE)
            }
        })

        viewModel.verifySuccess.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.verifySuccess.get() == false) {
                    showVerifyEmailDialog()
                }
            }

        })

        viewModel.loginSuccess.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val intent = Intent(this@LoginActivity, AfterRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        })


        if (viewModel.forgetPassIntentLiveData.hasActiveObservers())
            viewModel.forgetPassIntentLiveData.removeObservers(this)

        viewModel.forgetPassIntentLiveData.observe(
            this,
            Observer<Int> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("TYPE", it)
                startActivity(intent)
                finish()
            }
        )
    }


    private fun showAgreementDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.agreement_message)
            .setNeutralButton(getString(R.string.okey)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.cancel()
            }
            .create()
            .show()
    }


    private fun showRegisterSuccessDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.register_success_dialog)
            .setNeutralButton(getString(R.string.okey)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.cancel()
            }
            .create()
            .show()
    }


    private fun showVerifyEmailDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.send_verify_mail_again))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            viewModel.forgetPasswordClicked()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.setNeutralButton(getString(R.string.cancel)) { _, _ ->
            dialog?.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }




}
