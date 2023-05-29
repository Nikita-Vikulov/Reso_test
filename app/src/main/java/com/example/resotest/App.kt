package com.example.resotest

import android.app.Application
import com.example.resotest.api.ApiService
import com.example.resotest.api.RetrofitInstance
import com.example.resotest.model.Agencies
import com.example.resotest.repository.AgenciesMapper
import com.example.resotest.repository.AgenciesRepository
import com.example.resotest.repository.SubjectMapper
import com.example.resotest.repository.SubjectRepository
import com.example.resotest.room.ResoDatabase
import com.example.resotest.utils.AndroidNetworkStatus

class App : Application() {
    private val database by lazy { ResoDatabase.getDatabase(this) }
    private val apiService by lazy { RetrofitInstance.api }

    val agenciesRepository by lazy {
        AgenciesRepository(
            database.getAgenciesDao(),
            networkStatus = AndroidNetworkStatus(applicationContext),
            apiService = apiService,
            agenciesMapper = AgenciesMapper()
        )
    }
    val subjectRepository by lazy {
        SubjectRepository(
            database.getSubjectDao(),
            networkStatus = AndroidNetworkStatus(applicationContext),
            apiService = apiService,
            subjectMapper = SubjectMapper()
        )
    }
}