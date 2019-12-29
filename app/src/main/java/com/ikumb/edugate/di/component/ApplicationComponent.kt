package com.ikumb.edugate.di.component

import android.app.Application
import com.ikumb.edugate.EdugateApp
import com.ikumb.edugate.di.module.ActivityModule
import com.ikumb.edugate.di.module.ApplicationModule
import com.ikumb.edugate.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(edugateApp: EdugateApp)
}
