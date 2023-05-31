package com.example.resotest.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resotest.model.Agencies
import com.example.resotest.model.Subject

@Database(entities = [Agencies::class, Subject::class], version = 1, exportSchema = false)
abstract class ResoDatabase : RoomDatabase() {

    abstract fun getAgenciesDao(): AgenciesDao
    abstract fun getSubjectDao(): SubjectDao

}