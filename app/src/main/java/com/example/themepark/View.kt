package com.example.themepark

import com.example.themepark.models.LoginRequest
import com.example.themepark.models.LoginResponse
import com.example.themepark.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class UserRepository@Inject constructor(
    private val apiInterface:ApiInterface

){
    suspend fun login(loginRequest:LoginRequest): Response<LoginResponse> {
        return apiInterface.login(loginRequest)
    }
}
