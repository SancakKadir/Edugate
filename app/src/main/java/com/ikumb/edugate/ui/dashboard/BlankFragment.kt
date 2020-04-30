package com.ikumb.edugate.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseFragment
import com.ikumb.edugate.databinding.FragmentBlankBinding
import com.ikumb.edugate.databinding.FragmentDashboardBinding
import com.ikumb.edugate.di.Injectable

    class BlankFragment : BaseFragment<BlankViewModel, FragmentBlankBinding>(BlankViewModel::class.java),
    Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_blank

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

    }
}
