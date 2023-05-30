package com.example.resotest.repository


import com.example.resotest.api.ApiService
import com.example.resotest.model.Subject
import com.example.resotest.room.SubjectDao
import com.example.resotest.utils.INetworkStatus

class SubjectRepository(
    private val subjectDao: SubjectDao,
    private val networkStatus: INetworkStatus,
    private val apiService: ApiService,
    private val subjectMapper: SubjectMapper
) {
    private suspend fun insertAllSubject(
        subjectList: List<Subject>
    ) {
        val reposSubject = subjectMapper.mapSubject(subjectList)
        subjectDao.insertAll(reposSubject)
    }

    suspend fun getAllSubject(): List<Subject> {
        if (networkStatus.isNetworkAvailableNow()) {
            val responseSubject = apiService.getAllSubject()
            val subjectList: List<Subject> = responseSubject.body()?.toList() ?: emptyList()
            insertAllSubject(subjectList)
            return subjectDao.getAllSubject()
        } else {
            return subjectDao.getAllSubject()
        }
    }

    suspend fun getSubjectByName(querySubject: String): List<Subject> {
        val searchQuery = "%$querySubject%"
        return subjectDao.getSubjectByName(searchQuery)
    }
}