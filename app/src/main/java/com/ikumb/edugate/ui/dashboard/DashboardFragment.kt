package com.ikumb.edugate.ui.dashboard

import android.util.Log
import com.ikumb.edugate.R
import com.ikumb.edugate.core.BaseFragment
import com.ikumb.edugate.databinding.FragmentDashboardBinding
import com.ikumb.edugate.di.Injectable
import com.ikumb.edugate.utils.domain.observeWith


class DashboardFragment :
    BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java),
    Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        mBinding.viewModel?.getStudentsVisa()
        mBinding.viewModel?.usersLiveData?.observeWith(viewLifecycleOwner) {
            mBinding.textViewLessonVisaNote.text = it.analiz1
            mBinding.textViewLessonVisaDate.text = it.analizdate

            mBinding.textViewLessonVisaNote1.text = it.analitikgeometri
            mBinding.textViewLessonVisaDate1.text = it.analitikgeometridate

            mBinding.textViewLessonVisaNote2.text = it.bilgisayarbilimleri
            mBinding.textViewLessonVisaDate2.text = it.bilgisayarbilimleridate

            mBinding.textViewLessonVisaNote3.text = it.soyutmatematik
            mBinding.textViewLessonVisaDate3.text = it.soyutmatematikdate

            mBinding.textViewLessonVisaNote4.text = it.türkce
            mBinding.textViewLessonVisaDate4.text = it.türkcedate


            Log.d("data", it.toString())
        }
        mBinding.viewModel?.getStudentsFinal()
        mBinding.viewModel?.usersLiveDataFinal?.observeWith(viewLifecycleOwner) {
            mBinding.textViewLessonFinalNote.text = it.analiz1
            mBinding.textViewLessonFinalDate.text = it.analizdate

            mBinding.textViewLessonFinalNote1.text = it.analitikgeometri
            mBinding.textViewLessonFinalDate1.text = it.analitikgeometridate

            mBinding.textViewLessonFinalNote2.text = it.bilgisayarbilimleri
            mBinding.textViewLessonFinalDate2.text = it.bilgisayarbilimleridate

            mBinding.textViewLessonFinalNote3.text = it.soyutmatematik
            mBinding.textViewLessonFinalDate3.text = it.soyutmatematikdate

            mBinding.textViewLessonFinalNote4.text = it.türkce
            mBinding.textViewLessonFinalDate4.text = it.türkcedate
            Log.d("data", it.toString())
        }

    }

}
