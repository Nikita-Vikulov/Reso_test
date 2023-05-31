package com.example.resotest.api

import com.example.resotest.model.Agencies
import com.example.resotest.model.Subject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("subject")
    suspend fun getAllSubject(): Response<List<Subject>>

    @GET("agencies")
    suspend fun getAllAgencies(): Response<List<Agencies>>
}

