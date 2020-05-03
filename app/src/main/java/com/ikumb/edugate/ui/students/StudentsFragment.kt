package com.ikumb.edugate.ui.students

import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseFragment
import com.ikumb.edugate.databinding.FragmentStudentsBinding
import com.ikumb.edugate.utils.domain.observeWith

class StudentsFragment :
    BaseFragment<StudentsViewModel, FragmentStudentsBinding>(StudentsViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_students
    }

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        mBinding.viewModel?.getStudents()
        mBinding.viewModel?.usersLiveData?.observeWith(viewLifecycleOwner) {
            mBinding.textViewStudents.text = it.map { it.name }.toString()
        }
    }
}