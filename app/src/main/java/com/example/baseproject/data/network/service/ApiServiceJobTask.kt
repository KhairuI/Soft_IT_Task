package com.example.baseproject.data.network.service

import com.example.baseproject.data.network.ApiEndPoint
import com.example.baseproject.data.network.request.LoginRequest
import com.example.baseproject.data.network.request.NotificationRequest
import com.example.baseproject.data.network.response.LoginResponse
import com.example.baseproject.data.network.response.NotificationResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceJobTask {

    @POST(ApiEndPoint.ENDPOINT_API)
    suspend fun sendLogin(@Body request: LoginRequest): Response<LoginResponse>

}