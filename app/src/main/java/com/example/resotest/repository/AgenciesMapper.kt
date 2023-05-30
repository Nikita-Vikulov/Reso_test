package com.example.resotest.repository


import com.example.resotest.model.Agencies

class AgenciesMapper {
    fun mapAgencies(agencies: List<Agencies>): List<Agencies> {
        return agencies.map { repo ->
            repo.copy(
                idDistrict = (repo.idDistrict ?: "") as Int,
                status = repo.status ?: "",
                distance = (repo.distance ?: "") as Double,
                longitude = (repo.longitude ?: "") as Double,
                address = repo.address ?: "",
                shortName = repo.shortName ?: "",
                shortAddress = repo.shortAddress ?: "",
                distanceView = repo.distanceView ?: "",
                phone = repo.phone ?: "",
                workSchedule = repo.workSchedule ?: "",
                latitude = (repo.latitude ?: "") as Double,
                rel = repo.rel ?: "",
                timeZone = repo.timeZone ?: "",
                idTown = (repo.idTown ?: "") as Int,
                nodeId = (repo.nodeId ?: "") as Int,
                email = repo.email ?: "",
            )
        }
    }
}
