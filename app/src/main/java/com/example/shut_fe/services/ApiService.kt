package com.example.shut_fe.services

import com.example.shut_fe.models.*
import com.example.shut_fe.models.preference.Preference
import com.example.shut_fe.models.preference.PostPreference
import com.example.shut_fe.models.user.PostLogin
import com.example.shut_fe.models.user.PostUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users/login")
    suspend fun login(@Body post: PostLogin): Response<PostUser>

    @POST("users/")
    suspend fun postUser(@Body user: PostUser): Response<User>

    @GET("users/{userId}/preferences")
    suspend fun getPreference(@Path("userId") userId : Int): Response<PostPreference>

    @POST("preferences/{id}")
    suspend fun putPreference(@Path("id") id : Int, @Body preference: PostPreference): Response<PostPreference>
}
