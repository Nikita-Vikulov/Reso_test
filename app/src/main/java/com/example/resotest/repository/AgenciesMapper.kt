package com.example.resotest.repository


import com.example.resotest.model.Agencies

class AgenciesMapper {
    fun mapAgencies(agencies: List<Agencies>): List<Agencies> {
        return agencies.map { agencies ->
            agencies.copy(
                idDistrict = (agencies.idDistrict ?: "") as Int,
                status = agencies.status ?: "",
                distance = (agencies.distance ?: "") as Double,
                longitude = (agencies.longitude ?: "") as Double,
                address = agencies.address ?: "",
                shortName = agencies.shortName ?: "",
                shortAddress = agencies.shortAddress ?: "",
                distanceView = agencies.distanceView ?: "",
                phone = agencies.phone ?: "",
                workSchedule = agencies.workSchedule ?: "",
                latitude = (agencies.latitude ?: "") as Double,
                rel = agencies.rel ?: "",
                timeZone = agencies.timeZone ?: "",
                idTown = (agencies.idTown ?: "") as Int,
                nodeId = (agencies.nodeId ?: "") as Int,
                email = agencies.email ?: "",
            )
        }
    }
}
