package com.example.resotest.repository


import com.example.resotest.api.ApiService
import com.example.resotest.model.Agencies
import com.example.resotest.room.AgenciesDao
import com.example.resotest.utils.INetworkStatus


class AgenciesRepository(
    private val agenciesDao: AgenciesDao,
    private val networkStatus: INetworkStatus,
    private val apiService: ApiService,
    private val agenciesMapper: AgenciesMapper
) {
    private suspend fun insertAllAgencies(
        agencies: List<Agencies>
    ) {
        val reposAgencies = agenciesMapper.mapAgencies(agencies)
        agenciesDao.insertAll(reposAgencies)
    }

    suspend fun getAllAgencies(): List<Agencies> {
        if (networkStatus.isNetworkAvailableNow()) {
            val responseAgencies = apiService.getAllAgencies()
            val agenciesList: List<Agencies> = responseAgencies.body()?.toList() ?: emptyList()
            insertAllAgencies(agenciesList)
            return agenciesDao.getAllAgencies()
        } else {
            return agenciesDao.getAllAgencies()
        }
    }

    suspend fun getAgenciesById(nodeId: Int): List<Agencies> {
            return agenciesDao.getAgenciesById(nodeId)
    }
}