package com.ikumb.edugate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faskn.app.weatherapp.di.ViewModelFactory
import com.ikumb.edugate.di.key.ViewModelKey
import com.ikumb.edugate.ui.after_register.AfterRegisterViewModel
import com.ikumb.edugate.ui.dashboard.BlankViewModel
import com.ikumb.edugate.ui.dashboard.DashboardFragmentViewModel
import com.ikumb.edugate.ui.entry.EntryViewModel
import com.ikumb.edugate.ui.loginregister.LoginViewModel
import com.ikumb.edugate.ui.main.MainActivityViewModel
import com.ikumb.edugate.ui.splash.SplashFragmentViewModel
import com.ikumb.edugate.ui.students.StudentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DashboardFragmentViewModel::class)
    abstract fun provideDashboardFragmentViewModel(dashboardFragmentViewModel: DashboardFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BlankViewModel::class)
    abstract fun provideBlankViewModel(blankViewModel: BlankViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(EntryViewModel::class)
    abstract fun provideEntryViewModel(entryViewModel: EntryViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AfterRegisterViewModel::class)
    abstract fun providerAfterRegisterViewModel(afterRegisterViewModel: AfterRegisterViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(StudentsViewModel::class)
    abstract fun providerStudentsViewModel(studentsViewModel: StudentsViewModel): ViewModel


}
