package com.example.resotest.di.modules

import android.content.Context
import com.example.resotest.App
import com.example.resotest.api.ApiService
import com.example.resotest.repository.AgenciesMapper
import com.example.resotest.repository.AgenciesRepository
import com.example.resotest.repository.SubjectMapper
import com.example.resotest.repository.SubjectRepository
import com.example.resotest.room.ResoDatabase
import com.example.resotest.utils.AndroidNetworkStatus
import com.example.resotest.utils.INetworkStatus
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val application: App) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideAgenciesRepository(
        database: ResoDatabase,
        networkStatus: INetworkStatus,
        apiService: ApiService,
        agenciesMapper: AgenciesMapper
    ): AgenciesRepository {
        return AgenciesRepository(database.getAgenciesDao(), networkStatus, apiService, agenciesMapper)
    }

    @Provides
    fun provideSubjectRepository(
        database: ResoDatabase,
        networkStatus: INetworkStatus,
        apiService: ApiService,
        subjectMapper: SubjectMapper
    ): SubjectRepository {
        return SubjectRepository(database.getSubjectDao(), networkStatus, apiService, subjectMapper)
    }

    @Provides
    fun provideAgenciesMapper(): AgenciesMapper {
        return AgenciesMapper()
    }

    @Provides
    fun provideSubjectMapper(): SubjectMapper {
        return SubjectMapper()
    }

    @Provides
    fun provideNetworkStatus(): INetworkStatus {
        return AndroidNetworkStatus(application)
    }

}


