package com.example.resotest.repository

import com.example.resotest.model.Subject

class SubjectMapper {
    fun mapSubject(subject: List<Subject>): List<Subject> {
        return subject.map { subject ->
            subject.copy(
                name = subject.name ?: "",
                timeZone = subject.timeZone ?: "",
                order = (subject.order ?: "") as Int,
                nodeId = subject.nodeId ?: "",
                actionMyRegion = subject.actionMyRegion ?: ""
            )
        }
    }
}