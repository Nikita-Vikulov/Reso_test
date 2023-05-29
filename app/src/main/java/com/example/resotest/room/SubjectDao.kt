package com.example.resotest.room

import androidx.room.*
import com.example.resotest.model.Subject
import kotlinx.coroutines.flow.Flow


@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        subjectList: List<Subject>
    )

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM subject_table")
    fun getAllSubject(): Flow<List<Subject>>

    @Query("SELECT * FROM subject_table WHERE name = :name")
    suspend fun getSubjectByName(name: String): List<Subject>
}