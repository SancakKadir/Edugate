package com.ikumb.edugate.di.module

import com.ikumb.edugate.ui.dashboard.BlankFragment
import com.ikumb.edugate.ui.dashboard.DashboardFragment
import com.ikumb.edugate.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Furkan on 2019-10-26
 */

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeBlankFragment(): BlankFragment
}
