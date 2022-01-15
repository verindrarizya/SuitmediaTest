package com.verindrarizya.suitmediatest.data.remote

import com.verindrarizya.suitmediatest.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage:Int
    ): UserResponse

}