package com.ikumb.edugate.core

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.ikumb.edugate.utils.domain.toast
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun getLayoutRes(): Int

    lateinit var mAuth: FirebaseAuth
    var dialog: Dialog? = null



    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, viewModelProviderFactory).get(mViewModelClass)
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
        onInject()
        setupBindingLifecycleOwner()
        initFirebase()
        initToast()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */

    abstract fun initViewModel(viewModel: VM)

    private fun setupBindingLifecycleOwner() {
        binding.lifecycleOwner = this
    }

    fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    fun hideProgress() {
        runOnUiThread {
            dialog?.dismiss()
        }
    }

    fun showProgress() {
        runOnUiThread {
            dialog?.show()
        }
    }

    fun isShow(): Boolean? {
        return dialog?.isShowing
    }

    fun initToast() {
        if (viewModel.toastLiveData.hasActiveObservers())
            viewModel.toastLiveData.removeObservers(this)

        viewModel.toastLiveData.observe(
            this,
            Observer<String> {
                toast(it, Toast.LENGTH_LONG)
            }
        )
    }



}
