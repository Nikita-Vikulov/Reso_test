package com.example.resotest.di

import android.app.Application
import com.example.resotest.App
import com.example.resotest.di.modules.ApiModule
import com.example.resotest.di.modules.AppModule
import com.example.resotest.di.modules.DatabaseModule
import com.example.resotest.di.modules.ViewModelsModule
import com.example.resotest.repository.AgenciesRepository
import com.example.resotest.repository.SubjectRepository
import com.example.resotest.view.MainActivity
import com.example.resotest.view.agencies.AgenciesFragment
import com.example.resotest.view.details.DetailsFragment
import com.example.resotest.view.subject.SubjectFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        ViewModelsModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }

    override fun inject(application: App)
    fun inject(activity: MainActivity)
    fun inject(fragment: AgenciesFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: SubjectFragment)

    fun agenciesRepository(): AgenciesRepository
    fun subjectRepository(): SubjectRepository
}