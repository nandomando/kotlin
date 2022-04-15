package com.example.mytestapp.network

import com.example.mytestapp.model.FireBaseItem
import com.example.mytestapp.model.MItem
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MItemApi {
    @GET("plates")
    suspend fun getAllPlates(@Query("queryItem") query: String): FireBaseItem

}