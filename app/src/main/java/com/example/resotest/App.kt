package com.example.resotest

import android.app.Application
import com.example.resotest.di.AppComponent
import com.example.resotest.di.DaggerAppComponent
import com.example.resotest.di.modules.AppModule
import com.example.resotest.repository.AgenciesMapper
import com.example.resotest.repository.AgenciesRepository
import com.example.resotest.repository.SubjectMapper
import com.example.resotest.repository.SubjectRepository
import com.example.resotest.room.ResoDatabase
import javax.inject.Inject

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    @Inject
    lateinit var database: ResoDatabase

    @Inject
    lateinit var agenciesRepository: AgenciesRepository

    @Inject
    lateinit var subjectRepository: SubjectRepository

    @Inject
    lateinit var agenciesMapper: AgenciesMapper

    @Inject
    lateinit var subjectMapper: SubjectMapper

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .app(this)
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}