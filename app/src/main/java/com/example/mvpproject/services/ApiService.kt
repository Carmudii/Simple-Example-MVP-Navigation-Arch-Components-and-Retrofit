package com.example.mvpproject.services

import com.example.mvpproject.models.AuthResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/login") fun login(@Body request: RequestBody): Call<AuthResponse>
    @POST("users/register") fun register(@Body request: RequestBody): Call<AuthResponse>
}