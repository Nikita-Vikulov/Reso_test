package com.example.resotest.di.modules

import android.content.Context
import androidx.room.Room
import com.example.resotest.DATA_BASE
import com.example.resotest.room.AgenciesDao
import com.example.resotest.room.ResoDatabase
import com.example.resotest.room.SubjectDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): ResoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ResoDatabase::class.java,
            DATA_BASE
        ).build()
    }

    @Provides
    fun provideUsersDao(database: ResoDatabase): AgenciesDao {
        return database.getAgenciesDao()
    }

    @Provides
    fun provideReposDao(database: ResoDatabase): SubjectDao {
        return database.getSubjectDao()
    }
}
