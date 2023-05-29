package com.example.resotest.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.resotest.DATA_BASE
import com.example.resotest.model.Agencies
import com.example.resotest.model.Subject

@Database(entities = [Agencies::class, Subject::class], version = 1, exportSchema = false)
abstract class ResoDatabase : RoomDatabase() {

    abstract fun getAgenciesDao(): AgenciesDao
    abstract fun getSubjectDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: ResoDatabase? = null

        fun getDatabase(context: Context): ResoDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResoDatabase::class.java,
                    DATA_BASE
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}