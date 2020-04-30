package com.ikumb.edugate.ui.entry

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseApp
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseActivity
import com.ikumb.edugate.core.Constants
import com.ikumb.edugate.databinding.ActivityEntryBinding
import com.ikumb.edugate.ui.loginregister.LoginActivity

class EntryActivity :
    BaseActivity<EntryViewModel, ActivityEntryBinding>(EntryViewModel::class.java) {


    override fun getLayoutRes(): Int = R.layout.activity_entry


    override fun initViewModel(viewModel: EntryViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.entryActivityIntentLiveData.observe(
            this,
            Observer<Int>{
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra(Constants.IntentName.LOGIN_ACTIVITY_TYPE, it)
                startActivity(intent)
                finish()

            }
        )

    }


}
