package com.example.resotest.room

import androidx.room.*
import com.example.resotest.model.Subject


@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        subjectList: List<Subject>
    )

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM subject_table")
    suspend fun getAllSubject(): List<Subject>

    @Query("SELECT * FROM subject_table WHERE name LIKE :name")
    suspend fun getSubjectByName(name: String): List<Subject>
}