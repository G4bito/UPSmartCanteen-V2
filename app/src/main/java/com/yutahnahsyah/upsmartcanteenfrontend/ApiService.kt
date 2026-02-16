package com.yutahnahsyah.upsmartcanteenfrontend

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
  @POST("api/registerUser")
  suspend fun registerUser(@Body request: RegisterRequest): Response<AuthResponse>

  @POST("api/loginUser")
  suspend fun loginUser(@Body request: UserRequest): Response<AuthResponse>
}