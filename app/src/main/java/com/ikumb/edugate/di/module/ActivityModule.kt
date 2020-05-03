package com.ikumb.edugate.di.module

import com.ikumb.edugate.di.scope.PerActivity
import com.ikumb.edugate.ui.after_register.AfterRegisterActivity
import com.ikumb.edugate.ui.entry.EntryActivity
import com.ikumb.edugate.ui.loginregister.LoginActivity
import com.ikumb.edugate.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    /**
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun loginActivity(): LoginActivity


    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun entryActivity(): EntryActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun AfterRegisterActivity(): AfterRegisterActivity


}
