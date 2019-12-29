package com.ikumb.edugate.ui.dashboard

import com.ikumb.edugate.di.Injectable

import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseFragment
import com.ikumb.edugate.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java),
    Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
    }
}
