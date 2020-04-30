package com.ikumb.edugate.ui.entry

import androidx.lifecycle.MutableLiveData
import com.ikumb.edugate.core.BaseViewModel
import com.ikumb.edugate.core.Constants
import javax.inject.Inject


class EntryViewModel @Inject constructor() : BaseViewModel() {

    var entryActivityIntentLiveData = MutableLiveData<Int>()

    fun registerClick() {
        openLoginActivity(Constants.LoginActivityType.REGISTER_TYPE)
    }

    fun loginClick() {
        openLoginActivity(Constants.LoginActivityType.LOGIN_TYPE)
    }

    private fun openLoginActivity(viewType: Int) {
        entryActivityIntentLiveData.postValue(viewType)
    }
}