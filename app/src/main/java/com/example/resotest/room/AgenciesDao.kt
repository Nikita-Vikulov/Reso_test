package com.example.resotest.room

import androidx.room.*
import com.example.resotest.model.Agencies


@Dao
interface AgenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        agenciesList: List<Agencies>
    )

    @Delete
    suspend fun delete(agencies: Agencies)

    @Query("SELECT * FROM agencies_table")
    suspend fun getAllAgencies(): List<Agencies>

    @Query("SELECT * FROM agencies_table WHERE idDistrict LIKE :nodeId")
    suspend fun getAgenciesById(nodeId: Int): List<Agencies>
}