package com.example.resotest.repository

import com.example.resotest.model.Subject

class SubjectMapper {
    fun mapSubject(subject: List<Subject>): List<Subject> {
        return subject.map { repo ->
            repo.copy(
                name = repo.name?: "",
                timeZone = repo.timeZone?: "",
                order = (repo.order?: "") as Int,
                nodeId = repo.nodeId?: "",
                actionMyRegion = repo.actionMyRegion?: ""
            )
        }
    }
}