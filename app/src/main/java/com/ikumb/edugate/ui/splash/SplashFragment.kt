package com.ikumb.edugate.ui.splash

import androidx.databinding.Observable
import androidx.navigation.fragment.findNavController
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseFragment
import com.ikumb.edugate.databinding.FragmentSplashBinding
import com.ikumb.edugate.di.Injectable
import com.ikumb.edugate.utils.domain.observeWith
import com.ikumb.edugate.utils.extensions.toast

class SplashFragment :
    BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(SplashFragmentViewModel::class.java),
    Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_splash

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        mBinding.rootView.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_blankFragment)
        }

    }
}
