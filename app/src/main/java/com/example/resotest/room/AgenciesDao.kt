package com.example.resotest.room

import androidx.room.*
import com.example.resotest.model.Agencies
import kotlinx.coroutines.flow.Flow


@Dao
interface AgenciesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(agencies: Agencies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        agenciesList: List<Agencies>
    )

    @Delete
    suspend fun delete(agencies: Agencies)

    @Query("SELECT * FROM agencies_table")
    suspend fun getAllAgencies(): List<Agencies>

    @Query("SELECT * FROM agencies_table WHERE nodeId LIKE :nodeId")
    suspend fun getAgenciesById(nodeId: String): List<Agencies>
}