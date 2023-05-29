package com.example.resotest.api

import com.example.resotest.model.Agencies
import com.example.resotest.model.Subject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/subject")
    suspend fun getSearchSubject(
        @Query("q") query: String
    ): Response<Subject>

    @GET("subject")
    suspend fun getAllSubject(): Response<List<Subject>>

    @GET("/agencies/{nodeId}")
    suspend fun getAgenciesById(
        @Path("nodeId") nodeId: String,
    ): Response<Agencies>

    @GET("agencies")
    suspend fun getAllAgencies(): Response<List<Agencies>>
}

